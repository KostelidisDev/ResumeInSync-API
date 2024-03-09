package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.common.constants.security.Permission;
import gr.ihu.ict.resumeinsync.common.util.BooleanUtils;
import gr.ihu.ict.resumeinsync.common.util.ClassUtils;
import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.system.ProfileRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CustomPermissionEvaluatorImpl implements CustomPermissionEvaluator {

        private final EntityManager entityManager;
        private final ProfileRepository profileRepository;

        public CustomPermissionEvaluatorImpl(final EntityManager entityManager,
                        final ProfileRepository profileRepository) {
                this.entityManager = entityManager;
                this.profileRepository = profileRepository;
        }

        @Override
        public Try<EntityManager> getEntityManager() {
                return Try.success(entityManager);
        }

        private static Try<Tuple2<Permission, AbstractEntity>> validate(final Object targetDomainObject,
                        final Object permission) {
                return Try.of(() -> ClassUtils.isChildClass(Permission.class, permission.getClass()))
                                .flatMap(isValidPermission -> Try.of(() -> ClassUtils.isChildClass(
                                                AbstractEntity.class,
                                                targetDomainObject.getClass())).flatMap(isValidDomainObject -> {
                                                        if (!(isValidPermission)) {
                                                                return Try.failure(new Exception());
                                                        }

                                                        if (!(isValidDomainObject)) {
                                                                return Try.failure(new Exception());
                                                        }

                                                        return Try.success(
                                                                        Tuple.of(
                                                                                        (Permission) permission,
                                                                                        (AbstractEntity) targetDomainObject));
                                                }));
        }

        @Override
        public Try<Boolean> hasPermissionAnonymous(final Object targetDomainObject,
                        final Object permission) {
                return checkForReadPermission(targetDomainObject, permission);
        }

        @Override
        public Try<Boolean> hasPermissionAdmin(final Object targetDomainObject,
                        final Object permission) {
                return Try.success(Boolean.TRUE);
        }

        @Override
        public Try<Boolean> hasPermissionNonAdmin(final User authenticatedUser,
                        final Object targetDomainObject,
                        final Object permission) {
                return checkForReadPermission(targetDomainObject, permission)
                                .recover(throwable -> Boolean.FALSE)
                                .flatMap(aBoolean -> (aBoolean)
                                                ? Try.success(Boolean.TRUE)
                                                : Try.of(() -> targetDomainObject instanceof User)
                                                                .flatMap(isUserObject -> (isUserObject)
                                                                                ? Try.of(() -> (User) targetDomainObject)
                                                                                : Try.of(() -> (AbstractUserOwnedEntity) targetDomainObject)
                                                                                                .map(AbstractUserOwnedEntity::getUser))
                                                                .map(user -> user.equals(authenticatedUser)));
        }

        private Try<Boolean> checkForReadPermission(final Object targetDomainObject,
                        final Object permission) {
                return validate(targetDomainObject, permission)
                                .flatMap(permissionAbstractEntity -> {
                                        final Permission actualPermission = permissionAbstractEntity._1();
                                        final AbstractEntity abstractEntity = permissionAbstractEntity._2();

                                        return Try.of(() -> actualPermission.equals(Permission.READ))
                                                        .map(BooleanUtils::NOT)
                                                        .flatMap(isInvalidPermissionValue -> (isInvalidPermissionValue)
                                                                        ? Try.failure(new Exception())
                                                                        : Try.success(abstractEntity));
                                })
                                .flatMap(abstractEntity -> Try.of(abstractEntity::getClass)
                                                .flatMap(entityClass -> {
                                                        final Boolean isUserClass = ClassUtils.isChildClass(
                                                                        User.class,
                                                                        entityClass);

                                                        if (isUserClass) {
                                                                return Try.of(() -> (User) abstractEntity);
                                                        }

                                                        final Boolean isUserOwnedClass = ClassUtils.isChildClass(
                                                                        AbstractUserOwnedEntity.class,
                                                                        entityClass);

                                                        if (isUserOwnedClass) {
                                                                return Try.of(() -> (AbstractUserOwnedEntity) abstractEntity)
                                                                                .flatMap(userOwnedEntity -> Option.of(
                                                                                                userOwnedEntity.getUser())
                                                                                                .toTry());
                                                        }

                                                        return Try.failure(new Exception());
                                                }))
                                .flatMap(user -> Try.of(() -> profileRepository.getAllByUser(user)))
                                .map(List::ofAll)
                                .flatMap(profiles -> Try.of(() -> profiles.get(0)))
                                .flatMap(profile -> Option.of(profile.getPublished()).toTry());
        }
}

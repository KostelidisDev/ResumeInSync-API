package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.common.constants.security.UserRole;
import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

public interface CustomPermissionEvaluator extends PermissionEvaluator {

        Try<EntityManager> getEntityManager();

        Try<Boolean> hasPermissionAnonymous(Object targetDomainObject, Object permission);

        Try<Boolean> hasPermissionAdmin(Object targetDomainObject, Object permission);

        Try<Boolean> hasPermissionNonAdmin(User authenticatedUser,
                        Object targetDomainObject,
                        Object permission);

        @Override
        default boolean hasPermission(final Authentication authentication,
                        final Object targetDomainObject,
                        final Object permission) {
                return Match(AuthUtils.getAuthenticatedUser())
                                .of(
                                                Case($Success($()),
                                                                loggedInUser -> hasPermissionAuthenticated(loggedInUser,
                                                                                targetDomainObject, permission)),
                                                Case($Failure($()),
                                                                () -> hasPermissionAnonymous(targetDomainObject,
                                                                                permission)))
                                .getOrElse(Boolean.FALSE);
        }

        @Override
        default boolean hasPermission(final Authentication authentication,
                        final Serializable targetId,
                        final String targetType,
                        final Object permission) {
                return Try.of(() -> Class.forName(targetType))
                                .flatMap(aClass -> getEntityManager()
                                                .flatMap(entityManager -> Try
                                                                .of(() -> entityManager.find(aClass, targetId))))
                                .map(databaseEntity -> hasPermission(
                                                authentication,
                                                databaseEntity,
                                                permission))
                                .getOrElse(Boolean.FALSE);
        }

        default Try<Boolean> hasPermissionAuthenticated(final User loggedInUser,
                        final Object targetDomainObject,
                        final Object permission) {
                return Try.of(() -> hasRole(loggedInUser, UserRole.ADMIN.getValue()))
                                .flatMap(isAdmin -> (isAdmin)
                                                ? hasPermissionAdmin(targetDomainObject, permission)
                                                : hasPermissionNonAdmin(loggedInUser, targetDomainObject, permission));
        }

        default Boolean hasRole(final User user,
                        final String role) {
                return Option.of(user.getRoles()).toTry()
                                .map(List::ofAll)
                                .map(roles -> roles.map(Role::getName))
                                .map(Value::toJavaList)
                                .map(roles -> roles.contains(role))
                                .getOrElse(Boolean.FALSE);
        }
}

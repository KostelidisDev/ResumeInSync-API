package gr.ihu.ict.resumeinsync.service.crud;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.Objects;

public interface UserOwnedCrudService<ENTITY_TYPE extends AbstractUserOwnedEntity>
                extends CrudService<ENTITY_TYPE, UserOwnedRepository<ENTITY_TYPE>> {

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        default Try<Integer> getCountByUser(final User user) {
                return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                                .flatMap(ignored -> Try.of(this::getRepository))
                                .flatMap(repository -> Try.of(() -> repository.getCountByUser(user)));
        }

        @Override
        @PreAuthorize("hasPermission(#entity, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).CREATE)")
        default Try<ENTITY_TYPE> create(final ENTITY_TYPE entity) {
                return Try.success(entity)
                .flatMap(_entity -> {
                        final String importSource = Option.of(_entity.getImportSource()).toTry().getOrElse("ResumeInSync");
                        _entity.setImportSource(importSource);
                        return CrudService.super.create(_entity);
                });
        }

        @Override
        @PreAuthorize("hasPermission(#id, " +
                        "this.getEntityClassName(), " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        default Try<ENTITY_TYPE> findById(final String id) {
                return CrudService.super.findById(id);
        }

        @Override
        default Try<List<ENTITY_TYPE>> findByParameters(final Map<String, String[]> parameters) {
                return Try.run(() -> Objects.requireNonNull(parameters, "parameters is null"))
                                .flatMap(ignored -> AuthUtils.getAuthenticatedUser())
                                .flatMap(this::findByUser);
        }

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        default Try<List<ENTITY_TYPE>> findByUser(final User user) {
                return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                                .flatMap(ignored -> Try.of(this::getRepository))
                                .flatMap(repository -> Try.of(() -> repository.getAllByUser(user)))
                                .map(List::ofAll);
        }

        @Override
        @PreAuthorize("hasPermission(#id, " +
                        "this.getEntityClassName(), " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        default Try<ENTITY_TYPE> updateById(final ENTITY_TYPE entity, final String id) {
                return Try.run(() -> {
                        Objects.requireNonNull(entity, "entity is null");
                        Objects.requireNonNull(id, "id is null");
                }).flatMap(ignored -> findById(id))
                                .map(oldEntity -> {
                                        final String oldId = oldEntity.getId();
                                        final Date oldCreatedAt = oldEntity.getCreatedAt();
                                        final User oldUser = oldEntity.getUser();
                                        final String oldImportSource = Option.of(oldEntity.getImportSource()).toTry().getOrElse("ResumeInSync");

                                        entity.setId(oldId);
                                        entity.setCreatedAt(oldCreatedAt);
                                        entity.setUser(oldUser);
                                        entity.setImportSource(oldImportSource);

                                        return entity;
                                })
                                .flatMap(entity_type -> Try.of(this::getRepository)
                                                .flatMap(repository -> create(repository, entity)));
        }
}

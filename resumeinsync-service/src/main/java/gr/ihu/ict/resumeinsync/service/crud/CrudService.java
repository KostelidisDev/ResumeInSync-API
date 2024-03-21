package gr.ihu.ict.resumeinsync.service.crud;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Objects;

public interface CrudService<ENTITY_TYPE extends AbstractEntity, REPOSITORY_TYPE extends Repository<ENTITY_TYPE>> {

    REPOSITORY_TYPE getRepository();

    Logger getLogger();

    Class<ENTITY_TYPE> entityClass();

    default String getEntityClassName() {
        return Try.of(this::entityClass)
                .flatMap(entityClass -> Try.of(entityClass::getName))
                .getOrElseThrow(() -> new RuntimeException("Can't detect the entity's class name"));
    }

    default Try<List<ENTITY_TYPE>> sort(final List<ENTITY_TYPE> entities) {
        return Try.run(() -> Objects.requireNonNull(entities, "entities are null"))
                .map(ignored -> entities.toJavaList())
                .map(entity_types -> {
                    entity_types.sort(AbstractEntity::compareTo);
                    return entity_types;
                })
                .map(List::ofAll);
    }

    default Try<ENTITY_TYPE> create(final ENTITY_TYPE entity) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .map(ignored -> getRepository())
                .flatMap(repository -> create(repository, entity));
    }

    default Try<ENTITY_TYPE> create(final Repository<ENTITY_TYPE> repository, final ENTITY_TYPE entity) {
        return Try.of(() -> {
            if(repository == null) {
                return null;
            }
            if(entity == null) {
                return null;
            }
            return repository.save(entity);
        });
    }

    default Try<List<ENTITY_TYPE>> createAll(List<ENTITY_TYPE> entities) {
        return Try.sequence(entities.map(this::create)).map(Value::toList);
    }

    default Try<ENTITY_TYPE> findById(final String id) {
        return Try.run(() -> Objects.requireNonNull(id, "id is null"))
                .map(ignored -> getRepository())
                .flatMap(repository -> Option.ofOptional(repository.findOne(id)).toTry());
    }

    default Try<List<ENTITY_TYPE>> findByParameters(final Map<String, String[]> parameters) {
        return Try.run(() -> Objects.requireNonNull(parameters, "parameters is null"))
                .map(ignored -> getRepository())
                .map(JpaRepository::findAll)
                .map(List::ofAll)
                .flatMap(this::sort);
    }

    default Try<ENTITY_TYPE> updateById(final ENTITY_TYPE entity, final String id) {
        return Try.run(() -> {
            Objects.requireNonNull(entity, "entity is null");
            Objects.requireNonNull(id, "id is null");
        }).flatMap(ignored -> findById(id))
                .map(oldEntity -> {
                    final String oldId = oldEntity.getId();
                    final Date oldCreatedAt = oldEntity.getCreatedAt();

                    entity.setId(oldId);
                    entity.setCreatedAt(oldCreatedAt);

                    return entity;
                })
                .flatMap(entity_type -> Try.of(this::getRepository)
                        .flatMap(repository -> create(repository, entity)));
    }

    default Try<Void> deleteById(final String id) {
        return Try.run(() -> Objects.requireNonNull(id, "id is null"))
                .map(ignored -> getRepository())
                .flatMap(repository -> deleteById(repository, id));
    }

    default Try<Void> deleteById(final Repository<ENTITY_TYPE> repository, final String id) {
        return Try.run(() -> {
            if(repository == null) {
             return;         
            }
            if(id == null) {
                return;
            }
            repository.deleteById(id);
        });
    }

    default Try<Void> deleteAll(final List<String> ids) {
        return Try.run(() -> ids.map(this::deleteById));
    }
}

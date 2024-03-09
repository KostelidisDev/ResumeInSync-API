package gr.ihu.ict.resumeinsync.mapper;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import gr.ihu.ict.resumeinsync.dto.Reference;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class ReferenceMapper {

    private final EntityManager entityManager;

    public ReferenceMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T extends AbstractEntity> T resolve(final Reference reference,
            @TargetType final Class<T> entityClass) {
        if (Objects.isNull(reference)) {
            return null;
        }

        final Optional<T> entityOptional = Optional.ofNullable(entityManager.find(entityClass, reference.getId()));

        return entityOptional.orElseThrow(() -> new EntityNotFoundException(
                String.format(
                        "%s with %s=%s not found",
                        entityClass.getSimpleName(),
                        "id",
                        reference.getId())));
    }

    public Reference toReference(final AbstractEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        final Reference reference = new Reference();

        reference.setId(entity.getId());
        reference.setCreatedAt(entity.getCreatedAt());
        reference.setUpdatedAt(entity.getUpdatedAt());

        return reference;
    }

    public <T extends AbstractEntity> Set<T> resolve(final Set<Reference> references,
            @TargetType final Class<T> entityClass) {
        if (Objects.isNull(references)) {
            return null;
        }

        final Set<T> entities = new HashSet<>();

        for (Reference reference : references) {
            entities.add(resolve(reference, entityClass));
        }

        return entities;
    }

    public <T extends AbstractEntity> Set<Reference> toReferences(final Set<T> entities) {
        if (Objects.isNull(entities)) {
            return null;
        }

        final Set<Reference> references = new HashSet<>();

        for (AbstractEntity abstractEntity : entities) {
            references.add(toReference(abstractEntity));
        }

        return references;
    }
}

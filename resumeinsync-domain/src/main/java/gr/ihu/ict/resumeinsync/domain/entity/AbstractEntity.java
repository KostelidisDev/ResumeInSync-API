package gr.ihu.ict.resumeinsync.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public boolean equals(final Object abstractEntity) {
        boolean isValid = AbstractEntity.class.isAssignableFrom(abstractEntity.getClass());

        if (!(isValid)) {
            return false;
        }

        final AbstractEntity objCasted = (AbstractEntity) abstractEntity;

        final String myId = getId();
        final String objId = objCasted.getId();

        return myId.equals(objId);
    }

    public int compareTo(final Object abstractEntity) {
        boolean isValid = AbstractEntity.class.isAssignableFrom(abstractEntity.getClass());

        if (!(isValid)) {
            return 0;
        }

        final AbstractEntity castedAbstractUserEntity = (AbstractEntity) abstractEntity;

        final Date createdAt = Optional.ofNullable(getCreatedAt()).orElse(null);
        final Date otherCreatedAt = Optional.ofNullable(castedAbstractUserEntity.getCreatedAt()).orElse(null);

        if (createdAt == null || otherCreatedAt == null) {
            return 0;
        }

        return createdAt.compareTo(otherCreatedAt);
    }
}
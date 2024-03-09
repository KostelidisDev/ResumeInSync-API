package gr.ihu.ict.resumeinsync.domain.entity;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractUserOwnedEntity extends AbstractEntity {

    @ManyToOne
    private User user;


    @Column
    private String importSource;
}
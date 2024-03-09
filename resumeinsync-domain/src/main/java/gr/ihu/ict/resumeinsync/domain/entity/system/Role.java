package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Role")
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column
    private String name;
}

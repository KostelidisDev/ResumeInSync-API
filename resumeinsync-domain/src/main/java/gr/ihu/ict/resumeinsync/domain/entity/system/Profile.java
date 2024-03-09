package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Profile")
@Table(name = "profiles")
public class Profile extends AbstractUserOwnedEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String avatar;

    @Column
    private String mobilePhone;

    @Column
    private String landPhone;

    @Column
    private String fax;

    @Column
    private String bio;

    @Column
    private Boolean published;
}

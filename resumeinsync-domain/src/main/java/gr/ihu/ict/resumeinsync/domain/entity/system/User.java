package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private Boolean enabled;

    @Column
    private Boolean accountNonLocked;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}

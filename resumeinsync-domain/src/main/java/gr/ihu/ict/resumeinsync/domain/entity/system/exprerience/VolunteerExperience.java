package gr.ihu.ict.resumeinsync.domain.entity.system.exprerience;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "VolunteerExperience")
@Table(name = "volunteer_experiences")
public class VolunteerExperience extends Experience {

    @Column
    private String role;

    @Column
    private String organization;
}

package gr.ihu.ict.resumeinsync.domain.entity.system.exprerience;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "ProfessionalExperience")
@Table(name = "professional_experiences")
public class ProfessionalExperience extends Experience {

    @Column
    private String title;

    @Column
    private String company;
}

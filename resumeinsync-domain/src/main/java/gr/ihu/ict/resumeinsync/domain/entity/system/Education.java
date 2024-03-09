package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.embeddable.DateRange;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Education")
@Table(name = "educations")
public class Education extends AbstractUserOwnedEntity {

    @Column
    private String school;

    @Column
    private String degree;

    @Column
    private String field;

    @Embedded
    private DateRange dateRange;

    @Column
    private String grade;

    @Column
    private String description;
}

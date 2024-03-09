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
@Entity(name = "Certification")
@Table(name = "certifications")
public class Certification extends AbstractUserOwnedEntity {

    @Column
    private String name;

    @Column
    private String organization;

    @Column
    private Boolean canExpire;

    @Embedded
    private DateRange dateRange;

    @Column
    private String originalId;

    @Column
    private String originalUrl;
}

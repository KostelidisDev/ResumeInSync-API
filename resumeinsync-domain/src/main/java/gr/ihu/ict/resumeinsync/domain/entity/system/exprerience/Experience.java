package gr.ihu.ict.resumeinsync.domain.entity.system.exprerience;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.embeddable.DateRange;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Experience extends AbstractUserOwnedEntity {

    @Column
    private String location;

    @Column
    private Boolean currently;

    @Embedded
    private DateRange dateRange;

    @Column
    private String description;
}

package gr.ihu.ict.resumeinsync.dto.system.output.experience;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ExperienceOutputDTO extends AbstractUserOwnedEntityDTO {

    private String location;

    private Boolean currently;

    private DateRangeDTO dateRange;

    private String description;
}
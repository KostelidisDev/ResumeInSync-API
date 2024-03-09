package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationOutputDTO extends AbstractUserOwnedEntityDTO {

    private String school;

    private String degree;

    private String field;

    private DateRangeDTO dateRange;

    private String grade;

    private String description;
}

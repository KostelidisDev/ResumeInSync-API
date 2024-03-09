package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EducationInputDTO extends AbstractUserOwnedEntityDTO {

    @NotNull
    @Length(min = 1, max = 256)
    private String school;

    @NotNull
    @Length(max = 128)
    private String degree;

    @NotNull
    @Length(max = 128)
    private String field;

    @NotNull
    private DateRangeDTO dateRange;

    @NotNull
    @Length(max = 40)
    private String grade;

    @NotNull
    @Length(max = 1024)
    private String description;
}

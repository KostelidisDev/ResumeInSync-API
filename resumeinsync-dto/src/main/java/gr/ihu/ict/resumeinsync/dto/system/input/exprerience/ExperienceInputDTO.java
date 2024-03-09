package gr.ihu.ict.resumeinsync.dto.system.input.exprerience;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ExperienceInputDTO extends AbstractUserOwnedEntityDTO {

    @Length(max = 40)
    private String location;

    @NotNull
    private Boolean currently;

    @NotNull
    private DateRangeDTO dateRange;

    @Length(max = 1024)
    private String description;
}
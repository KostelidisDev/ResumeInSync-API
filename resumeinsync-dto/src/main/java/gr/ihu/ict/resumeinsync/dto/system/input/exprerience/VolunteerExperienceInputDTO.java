package gr.ihu.ict.resumeinsync.dto.system.input.exprerience;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VolunteerExperienceInputDTO extends ExperienceInputDTO {

    @NotNull
    @Length(min = 1, max = 40)
    private String role;

    @NotNull
    @Length(min = 1, max = 40)
    private String organization;
}
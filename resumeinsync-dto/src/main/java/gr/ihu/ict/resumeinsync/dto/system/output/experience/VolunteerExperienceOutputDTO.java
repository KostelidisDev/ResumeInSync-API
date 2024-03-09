package gr.ihu.ict.resumeinsync.dto.system.output.experience;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolunteerExperienceOutputDTO extends ExperienceOutputDTO {

    private String role;

    private String organization;
}
package gr.ihu.ict.resumeinsync.dto.system.output.experience;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalExperienceOutputDTO extends ExperienceOutputDTO {

    private String title;

    private String company;
}

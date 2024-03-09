package gr.ihu.ict.resumeinsync.api.controller.crud.experience.volunteer;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.dto.system.input.exprerience.VolunteerExperienceInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.VolunteerExperienceOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences/volunteers")
public interface VolunteerExperienceCrudController
                extends
                UserOwnedCrudController<VolunteerExperienceInputDTO, VolunteerExperienceOutputDTO, VolunteerExperience> {
}

package gr.ihu.ict.resumeinsync.api.controller.crud.experience.professional;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.ProfessionalExperience;
import gr.ihu.ict.resumeinsync.dto.system.input.exprerience.ProfessionalExperienceInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.ProfessionalExperienceOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences/professionals")
public interface ProfessionalExperienceCrudController
                extends
                UserOwnedCrudController<ProfessionalExperienceInputDTO, ProfessionalExperienceOutputDTO, ProfessionalExperience> {
}

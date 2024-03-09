package gr.ihu.ict.resumeinsync.api.controller.crud.education;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Education;
import gr.ihu.ict.resumeinsync.dto.system.input.EducationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.EducationOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
public interface EducationCrudController
                extends UserOwnedCrudController<EducationInputDTO, EducationOutputDTO, Education> {
}

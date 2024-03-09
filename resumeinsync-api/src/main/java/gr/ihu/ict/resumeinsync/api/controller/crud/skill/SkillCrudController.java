package gr.ihu.ict.resumeinsync.api.controller.crud.skill;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Skill;
import gr.ihu.ict.resumeinsync.dto.system.input.SkillInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.SkillOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
public interface SkillCrudController
                extends UserOwnedCrudController<SkillInputDTO, SkillOutputDTO, Skill> {
}

package gr.ihu.ict.resumeinsync.api.controller.crud.skill;

import gr.ihu.ict.resumeinsync.domain.entity.system.Skill;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.SkillInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.SkillOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.SkillInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.SkillOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.skill.SkillCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@Slf4j
public class SkillCrudControllerImpl implements SkillCrudController {

    private final SkillCrudService skillCrudService;
    private final SkillInputMapper skillInputMapper;
    private final SkillOutputMapper skillOutputMapper;

    public SkillCrudControllerImpl(final SkillCrudService skillCrudService,
            final SkillInputMapper skillInputMapper,
            final SkillOutputMapper skillOutputMapper) {
        this.skillCrudService = skillCrudService;
        this.skillInputMapper = skillInputMapper;
        this.skillOutputMapper = skillOutputMapper;
    }

    @Override
    public CrudService<Skill, UserOwnedRepository<Skill>> getCrudService() {
        return skillCrudService;
    }

    @Override
    public Mapper<Skill, SkillInputDTO> getInputMapperDTO() {
        return skillInputMapper;
    }

    @Override
    public Mapper<Skill, SkillOutputDTO> getOutputMapperDTO() {
        return skillOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

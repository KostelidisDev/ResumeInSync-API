package gr.ihu.ict.resumeinsync.api.controller.crud.experience.professional;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.ProfessionalExperience;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.exprerience.ProfessionalExperienceInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.ProfessionalExperienceOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.experience.ProfessionalExperienceInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.experience.ProfessionalExperienceOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.professional.ProfessionalExperienceCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences/professionals")
@Slf4j
public class ProfessionalExperienceCrudControllerImpl implements ProfessionalExperienceCrudController {

    private final ProfessionalExperienceCrudService professionalExperienceCrudService;
    private final ProfessionalExperienceInputMapper professionalExperienceInputMapper;
    private final ProfessionalExperienceOutputMapper professionalExperienceOutputMapper;

    public ProfessionalExperienceCrudControllerImpl(
            final ProfessionalExperienceCrudService professionalExperienceCrudService,
            final ProfessionalExperienceInputMapper professionalExperienceInputMapper,
            final ProfessionalExperienceOutputMapper professionalExperienceOutputMapper) {
        this.professionalExperienceCrudService = professionalExperienceCrudService;
        this.professionalExperienceInputMapper = professionalExperienceInputMapper;
        this.professionalExperienceOutputMapper = professionalExperienceOutputMapper;
    }

    @Override
    public CrudService<ProfessionalExperience, UserOwnedRepository<ProfessionalExperience>> getCrudService() {
        return professionalExperienceCrudService;
    }

    @Override
    public Mapper<ProfessionalExperience, ProfessionalExperienceInputDTO> getInputMapperDTO() {
        return professionalExperienceInputMapper;
    }

    @Override
    public Mapper<ProfessionalExperience, ProfessionalExperienceOutputDTO> getOutputMapperDTO() {
        return professionalExperienceOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

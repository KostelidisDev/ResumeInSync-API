package gr.ihu.ict.resumeinsync.api.controller.crud.experience.volunteer;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.exprerience.VolunteerExperienceInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.VolunteerExperienceOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.experience.VolunteerExperienceInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.experience.VolunteerExperienceOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.volunteer.VolunteerExperienceCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences/volunteers")
@Slf4j
public class VolunteerExperienceCrudControllerImpl implements VolunteerExperienceCrudController {

    private final VolunteerExperienceCrudService volunteerExperienceCrudService;
    private final VolunteerExperienceInputMapper volunteerExperienceInputMapper;
    private final VolunteerExperienceOutputMapper volunteerExperienceOutputMapper;

    public VolunteerExperienceCrudControllerImpl(final VolunteerExperienceCrudService volunteerExperienceCrudService,
            final VolunteerExperienceInputMapper volunteerExperienceInputMapper,
            final VolunteerExperienceOutputMapper volunteerExperienceOutputMapper) {
        this.volunteerExperienceCrudService = volunteerExperienceCrudService;
        this.volunteerExperienceInputMapper = volunteerExperienceInputMapper;
        this.volunteerExperienceOutputMapper = volunteerExperienceOutputMapper;
    }

    @Override
    public CrudService<VolunteerExperience, UserOwnedRepository<VolunteerExperience>> getCrudService() {
        return volunteerExperienceCrudService;
    }

    @Override
    public Mapper<VolunteerExperience, VolunteerExperienceInputDTO> getInputMapperDTO() {
        return volunteerExperienceInputMapper;
    }

    @Override
    public Mapper<VolunteerExperience, VolunteerExperienceOutputDTO> getOutputMapperDTO() {
        return volunteerExperienceOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

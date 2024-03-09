package gr.ihu.ict.resumeinsync.api.controller.crud.education;

import gr.ihu.ict.resumeinsync.domain.entity.system.Education;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.EducationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.EducationOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.EducationInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.EducationOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.education.EducationCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
@Slf4j
public class EducationCrudControllerImpl implements EducationCrudController {

    private final EducationCrudService educationCrudService;
    private final EducationInputMapper educationInputMapper;
    private final EducationOutputMapper educationOutputMapper;

    public EducationCrudControllerImpl(final EducationCrudService educationCrudService,
            final EducationInputMapper educationInputMapper,
            final EducationOutputMapper educationOutputMapper) {
        this.educationCrudService = educationCrudService;
        this.educationInputMapper = educationInputMapper;
        this.educationOutputMapper = educationOutputMapper;
    }

    @Override
    public CrudService<Education, UserOwnedRepository<Education>> getCrudService() {
        return educationCrudService;
    }

    @Override
    public Mapper<Education, EducationInputDTO> getInputMapperDTO() {
        return educationInputMapper;
    }

    @Override
    public Mapper<Education, EducationOutputDTO> getOutputMapperDTO() {
        return educationOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

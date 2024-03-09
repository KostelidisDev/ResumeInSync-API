package gr.ihu.ict.resumeinsync.api.controller.crud.certification;

import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.CertificationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.CertificationOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.CertificationsInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.CertificationsOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.certification.CertificationCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certifications")
@Slf4j
public class CertificationCrudControllerImpl implements CertificationCrudController {

    private final CertificationCrudService certificationCrudService;
    private final CertificationsInputMapper certificationsInputMapper;
    private final CertificationsOutputMapper certificationsOutputMapper;

    public CertificationCrudControllerImpl(final CertificationCrudService certificationCrudService,
            final CertificationsInputMapper certificationsInputMapper,
            final CertificationsOutputMapper certificationsOutputMapper) {
        this.certificationCrudService = certificationCrudService;
        this.certificationsInputMapper = certificationsInputMapper;
        this.certificationsOutputMapper = certificationsOutputMapper;
    }

    @Override
    public CrudService<Certification, UserOwnedRepository<Certification>> getCrudService() {
        return certificationCrudService;
    }

    @Override
    public Mapper<Certification, CertificationInputDTO> getInputMapperDTO() {
        return certificationsInputMapper;
    }

    @Override
    public Mapper<Certification, CertificationOutputDTO> getOutputMapperDTO() {
        return certificationsOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

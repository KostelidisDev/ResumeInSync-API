package gr.ihu.ict.resumeinsync.api.controller.crud.publication;

import gr.ihu.ict.resumeinsync.domain.entity.system.Publication;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.PublicationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.PublicationOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.PublicationInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.PublicationOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.publication.PublicationCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publications")
@Slf4j
public class PublicationCrudControllerImpl implements PublicationCrudController {

    private final PublicationCrudService publicationCrudService;
    private final PublicationInputMapper publicationInputMapper;
    private final PublicationOutputMapper publicationOutputMapper;

    public PublicationCrudControllerImpl(final PublicationCrudService publicationCrudService,
            final PublicationInputMapper publicationInputMapper,
            final PublicationOutputMapper publicationOutputMapper) {
        this.publicationCrudService = publicationCrudService;
        this.publicationInputMapper = publicationInputMapper;
        this.publicationOutputMapper = publicationOutputMapper;
    }

    @Override
    public CrudService<Publication, UserOwnedRepository<Publication>> getCrudService() {
        return publicationCrudService;
    }

    @Override
    public Mapper<Publication, PublicationInputDTO> getInputMapperDTO() {
        return publicationInputMapper;
    }

    @Override
    public Mapper<Publication, PublicationOutputDTO> getOutputMapperDTO() {
        return publicationOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

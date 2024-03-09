package gr.ihu.ict.resumeinsync.service.crud.publication;

import gr.ihu.ict.resumeinsync.domain.entity.system.Publication;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublicationCrudServiceImpl implements PublicationCrudService {

    private final PublicationRepository publicationRepository;

    public PublicationCrudServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public UserOwnedRepository<Publication> getRepository() {
        return publicationRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Publication> entityClass() {
        return Publication.class;
    }
}

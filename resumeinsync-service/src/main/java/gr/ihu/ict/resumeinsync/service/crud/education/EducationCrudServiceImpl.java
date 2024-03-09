package gr.ihu.ict.resumeinsync.service.crud.education;

import gr.ihu.ict.resumeinsync.domain.entity.system.Education;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.EducationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EducationCrudServiceImpl implements EducationCrudService {

    private final EducationRepository educationRepository;

    public EducationCrudServiceImpl(final EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public UserOwnedRepository<Education> getRepository() {
        return educationRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Education> entityClass() {
        return Education.class;
    }
}

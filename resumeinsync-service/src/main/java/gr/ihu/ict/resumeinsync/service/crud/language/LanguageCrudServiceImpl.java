package gr.ihu.ict.resumeinsync.service.crud.language;

import gr.ihu.ict.resumeinsync.domain.entity.system.Language;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LanguageCrudServiceImpl implements LanguageCrudService {

    private final LanguageRepository languageRepository;

    public LanguageCrudServiceImpl(final LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public UserOwnedRepository<Language> getRepository() {
        return languageRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Language> entityClass() {
        return Language.class;
    }
}

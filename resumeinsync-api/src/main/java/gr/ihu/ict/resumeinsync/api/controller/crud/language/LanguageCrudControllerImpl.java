package gr.ihu.ict.resumeinsync.api.controller.crud.language;

import gr.ihu.ict.resumeinsync.domain.entity.system.Language;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.LanguageInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.LanguageOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.LanguageInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.LanguageOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.language.LanguageCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages")
@Slf4j
public class LanguageCrudControllerImpl implements LanguageCrudController {

    private final LanguageCrudService languageCrudService;
    private final LanguageInputMapper languageInputMapper;
    private final LanguageOutputMapper languageOutputMapper;

    public LanguageCrudControllerImpl(final LanguageCrudService languageCrudService,
            final LanguageInputMapper languageInputMapper,
            final LanguageOutputMapper languageOutputMapper) {
        this.languageCrudService = languageCrudService;
        this.languageInputMapper = languageInputMapper;
        this.languageOutputMapper = languageOutputMapper;
    }

    @Override
    public CrudService<Language, UserOwnedRepository<Language>> getCrudService() {
        return languageCrudService;
    }

    @Override
    public Mapper<Language, LanguageInputDTO> getInputMapperDTO() {
        return languageInputMapper;
    }

    @Override
    public Mapper<Language, LanguageOutputDTO> getOutputMapperDTO() {
        return languageOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}

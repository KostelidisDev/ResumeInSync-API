package gr.ihu.ict.resumeinsync.api.controller.crud.language;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Language;
import gr.ihu.ict.resumeinsync.dto.system.input.LanguageInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.LanguageOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages")
public interface LanguageCrudController
                extends UserOwnedCrudController<LanguageInputDTO, LanguageOutputDTO, Language> {
}

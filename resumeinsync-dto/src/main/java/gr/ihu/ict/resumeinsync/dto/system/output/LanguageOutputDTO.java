package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.common.constants.LanguageProficiency;
import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageOutputDTO extends AbstractUserOwnedEntityDTO {

    private String name;

    private LanguageProficiency proficiency;
}

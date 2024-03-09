package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillOutputDTO extends AbstractUserOwnedEntityDTO {

    private String name;
}

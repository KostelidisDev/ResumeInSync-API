package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SkillInputDTO extends AbstractUserOwnedEntityDTO {

    @NotNull
    @Length(min = 1, max = 40)
    private String name;

}

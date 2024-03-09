package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleInputDTO extends AbstractEntityDTO {

    @NotNull
    @Length(min = 1, max = 40)
    private String name;
}

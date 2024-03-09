package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserInputDTO extends AbstractEntityDTO {

    @NotNull
    @Length(min = 4, max = 16)
    private String username;

    @NotNull
    @Email
    @Length(min = 5, max = 40)
    private String email;

    @NotNull
    @Length(min = 8, max = 32)
    private String password;
}

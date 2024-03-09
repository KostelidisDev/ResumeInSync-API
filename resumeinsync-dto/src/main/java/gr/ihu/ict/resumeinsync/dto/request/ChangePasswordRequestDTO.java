package gr.ihu.ict.resumeinsync.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordRequestDTO {

    @NotNull
    @Length(min = 4, max = 32)
    private String currentPassword;

    @NotNull
    @Length(min = 8, max = 64)
    private String newPassword;
}

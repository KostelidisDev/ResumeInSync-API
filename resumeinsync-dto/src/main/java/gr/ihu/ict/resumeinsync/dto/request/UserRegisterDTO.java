package gr.ihu.ict.resumeinsync.dto.request;

import gr.ihu.ict.resumeinsync.dto.system.input.ProfileInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.input.UserInputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegisterDTO {

    @NotNull
    private UserInputDTO user;

    @NotNull
    private ProfileInputDTO profile;
}

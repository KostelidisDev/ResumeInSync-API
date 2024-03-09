package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProfileInputDTO extends AbstractUserOwnedEntityDTO {

    @NotNull
    @Length(min = 3, max = 40)
    private String firstName;

    @NotNull
    @Length(min = 3, max = 40)
    private String lastName;

    @NotNull
    @Length(max = 1024)
    private String avatar;

    @NotNull
    @Length(max = 20)
    private String mobilePhone;

    @NotNull
    @Length(max = 20)
    private String landPhone;

    @NotNull
    @Length(max = 20)
    private String fax;

    @NotNull
    private String bio;

    @NotNull
    private Boolean published;
}

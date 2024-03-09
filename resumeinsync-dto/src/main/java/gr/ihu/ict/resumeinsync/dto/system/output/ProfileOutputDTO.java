package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileOutputDTO extends AbstractUserOwnedEntityDTO {

    private String firstName;

    private String lastName;

    private String avatar;

    private String mobilePhone;

    private String landPhone;

    private String fax;

    private String bio;

    private Boolean published;
}

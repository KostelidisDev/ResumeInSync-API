package gr.ihu.ict.resumeinsync.dto.response;

import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeResponse {

    private UserOutputDTO user;

    private ProfileOutputDTO profile;
}

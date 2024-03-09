package gr.ihu.ict.resumeinsync.api.controller.crud.profile;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.dto.system.input.ProfileInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public interface ProfileCrudController
                extends UserOwnedCrudController<ProfileInputDTO, ProfileOutputDTO, Profile> {
}

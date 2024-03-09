package gr.ihu.ict.resumeinsync.api.controller.rpc.profile;

import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.system.output.ProfileOutputMapper;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import gr.ihu.ict.resumeinsync.service.rpc.profile.ProfileRpcService;
import io.vavr.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rpc/profile")
public class ProfileRpcControllerImpl implements ProfileRpcController {

    private final ProfileCrudService profileCrudService;
    private final ProfileRpcService profileRpcService;
    private final ProfileOutputMapper profileOutputMapper;

    public ProfileRpcControllerImpl(final ProfileCrudService profileCrudService,
            final ProfileRpcService profileRpcService,
            final ProfileOutputMapper profileOutputMapper) {
        this.profileCrudService = profileCrudService;
        this.profileRpcService = profileRpcService;
        this.profileOutputMapper = profileOutputMapper;
    }

    @Override
    @PostMapping("/publish")
    public ResponseEntity<ProfileOutputDTO> publish() {
        return AuthUtils.getAuthenticatedUser()
                .flatMap(profileRpcService::publish)
                .map(profileOutputMapper::toDTO)
                .map(ResponseEntity::ok)
                .get();
    }

    @Override
    @PostMapping("/unpublish")
    public ResponseEntity<ProfileOutputDTO> unpublish() {
        return AuthUtils.getAuthenticatedUser()
                .flatMap(profileRpcService::unpublish)
                .map(profileOutputMapper::toDTO)
                .map(ResponseEntity::ok)
                .get();
    }

    @Override
    @GetMapping("/published")
    public ResponseEntity<List<ProfileOutputDTO>> published() {
        return profileCrudService.findAllPublished()
                .map(Value::toJavaList)
                .map(profileOutputMapper::toDTO)
                .map(ResponseEntity::ok)
                .get();
    }
}

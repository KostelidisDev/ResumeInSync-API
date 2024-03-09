package gr.ihu.ict.resumeinsync.api.controller.rpc.auth;

import gr.ihu.ict.resumeinsync.dto.request.ChangePasswordRequestDTO;
import gr.ihu.ict.resumeinsync.dto.request.UserRegisterDTO;
import gr.ihu.ict.resumeinsync.dto.response.MeResponse;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.system.input.ProfileInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.UserInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.ProfileOutputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.UserOutputMapper;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import gr.ihu.ict.resumeinsync.service.rpc.auth.AuthRpcService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rpc/auth")
public class AuthRpcControllerImpl implements AuthRpcController {

        private final AuthRpcService authRpcService;
        private final UserInputMapper userInputMapper;
        private final UserOutputMapper userOutputMapper;
        private final ProfileInputMapper profileInputMapper;
        private final ProfileOutputMapper profileOutputMapper;
        private final ProfileCrudService profileCrudService;

        public AuthRpcControllerImpl(final AuthRpcService authRpcService,
                        final UserInputMapper userInputMapper,
                        final UserOutputMapper userOutputMapper,
                        final ProfileInputMapper profileInputMapper,
                        final ProfileOutputMapper profileOutputMapper,
                        final ProfileCrudService profileCrudService) {
                this.authRpcService = authRpcService;
                this.userInputMapper = userInputMapper;
                this.userOutputMapper = userOutputMapper;
                this.profileInputMapper = profileInputMapper;
                this.profileOutputMapper = profileOutputMapper;
                this.profileCrudService = profileCrudService;
        }

        @Override
        @PostMapping("/register")
        public ResponseEntity<UserOutputDTO> register(@RequestBody @Valid final UserRegisterDTO userRegisterDTO) {
                return Option.of(userRegisterDTO.getUser())
                                .toTry()
                                .flatMap(userInputDTO -> Try.of(() -> userInputMapper.fromDTO(userInputDTO)))
                                .flatMap(user -> Option.of(userRegisterDTO.getProfile()).toTry()
                                                .flatMap(profileInputDTO -> Try
                                                                .of(() -> profileInputMapper.fromDTO(profileInputDTO)))
                                                .flatMap(profile -> authRpcService.register(
                                                                user,
                                                                profile)))
                                .map(userOutputMapper::toDTO)
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping("/me")
        public ResponseEntity<MeResponse> me() {
                return AuthUtils.getAuthenticatedUser()
                                .flatMap(user -> profileCrudService.getByUser(user)
                                                .flatMap(profile -> Try.of(() -> profileOutputMapper.toDTO(profile)))
                                                .flatMap(profileOutputDTO -> Try.of(() -> userOutputMapper.toDTO(user))
                                                                .flatMap(userOutputDTO -> Try.of(MeResponse::builder)
                                                                                .map(meResponseBuilder -> meResponseBuilder
                                                                                                .user(userOutputDTO)
                                                                                                .profile(profileOutputDTO)))))
                                .map(MeResponse.MeResponseBuilder::build)
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @PostMapping("/change-password")
        public ResponseEntity<UserOutputDTO> changePassword(
                        @RequestBody @Valid final ChangePasswordRequestDTO changePasswordRequestDTO) {
                return Option.of(changePasswordRequestDTO.getCurrentPassword())
                                .toTry()
                                .flatMap(currentPassword -> Option.of(changePasswordRequestDTO.getNewPassword())
                                                .toTry()
                                                .flatMap(newPassword -> AuthUtils.getAuthenticatedUser()
                                                                .flatMap(user -> authRpcService.changePassword(
                                                                                user,
                                                                                currentPassword,
                                                                                newPassword))))
                                .map(userOutputMapper::toDTO)
                                .map(ResponseEntity::ok)
                                .get();
        }
}

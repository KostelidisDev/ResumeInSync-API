package gr.ihu.ict.resumeinsync.api.controller.rpc.auth;

import gr.ihu.ict.resumeinsync.dto.request.ChangePasswordRequestDTO;
import gr.ihu.ict.resumeinsync.dto.request.UserRegisterDTO;
import gr.ihu.ict.resumeinsync.dto.response.MeResponse;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rpc/auth")
public interface AuthRpcController {

    @PostMapping("/register")
    ResponseEntity<UserOutputDTO> register(@RequestBody @Valid final UserRegisterDTO userRegisterDTO);

    @GetMapping("/me")
    ResponseEntity<MeResponse> me();

    @PostMapping("/change-password")
    ResponseEntity<UserOutputDTO> changePassword(
            @RequestBody @Valid final ChangePasswordRequestDTO changePasswordRequestDTO);
}

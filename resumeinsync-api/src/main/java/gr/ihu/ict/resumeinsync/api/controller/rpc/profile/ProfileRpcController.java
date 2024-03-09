package gr.ihu.ict.resumeinsync.api.controller.rpc.profile;

import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rpc/profile")
public interface ProfileRpcController {

    @PostMapping("/publish")
    ResponseEntity<ProfileOutputDTO> publish();

    @PostMapping("/unpublish")
    ResponseEntity<ProfileOutputDTO> unpublish();

    @GetMapping("/published")
    ResponseEntity<List<ProfileOutputDTO>> published();
}

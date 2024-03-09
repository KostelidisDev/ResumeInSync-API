package gr.ihu.ict.resumeinsync.api.controller.rpc.dashboard;

import gr.ihu.ict.resumeinsync.dto.response.DashboardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpc/dashboard")
public interface DashboardRpcController {

    @GetMapping({ "/", "" })
    ResponseEntity<DashboardDTO> get();
}

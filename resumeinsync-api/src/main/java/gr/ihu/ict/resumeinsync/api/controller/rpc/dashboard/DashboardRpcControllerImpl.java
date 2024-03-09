package gr.ihu.ict.resumeinsync.api.controller.rpc.dashboard;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import gr.ihu.ict.resumeinsync.dto.response.DashboardDTO;
import gr.ihu.ict.resumeinsync.mapper.other.DashboardMapper;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import gr.ihu.ict.resumeinsync.service.rpc.dashboard.DashboardRpcService;
import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpc/dashboard")
public class DashboardRpcControllerImpl implements DashboardRpcController {

    private final DashboardRpcService dashboardRpcService;
    private final DashboardMapper dashboardMapper;

    public DashboardRpcControllerImpl(final DashboardRpcService dashboardRpcService,
            final DashboardMapper dashboardMapper) {
        this.dashboardRpcService = dashboardRpcService;
        this.dashboardMapper = dashboardMapper;
    }

    @Override
    @GetMapping({ "/", "" })
    public ResponseEntity<DashboardDTO> get() {
        return AuthUtils.getAuthenticatedUser()
                .map(AbstractEntity::getId)
                .flatMap(dashboardRpcService::get)
                .flatMap(dashboard -> Try.of(() -> dashboardMapper.toDTO(dashboard)))
                .map(ResponseEntity::ok)
                .get();
    }
}

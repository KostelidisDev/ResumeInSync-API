package gr.ihu.ict.resumeinsync.service.rpc.dashboard;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.model.Dashboard;
import io.vavr.control.Try;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DashboardRpcService {

        @PreAuthorize("hasPermission(#userId, " +
                        "T(gr.ihu.ict.resumeinsync.domain.constant.Entities).USER, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Dashboard> get(String userId);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Dashboard> get(User user);
}
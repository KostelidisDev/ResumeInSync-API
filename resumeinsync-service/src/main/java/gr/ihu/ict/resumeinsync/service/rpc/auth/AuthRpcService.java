package gr.ihu.ict.resumeinsync.service.rpc.auth;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import io.vavr.control.Try;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public interface AuthRpcService {

        Try<User> register(User user,
                        Profile profile);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<User> changePassword(User user,
                        String currentPassword,
                        String newPassword);
}

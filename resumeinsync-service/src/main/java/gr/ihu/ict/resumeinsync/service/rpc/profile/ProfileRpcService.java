package gr.ihu.ict.resumeinsync.service.rpc.profile;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import io.vavr.control.Try;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public interface ProfileRpcService {

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Profile> publish(final User user);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Profile> unpublish(final User user);
}

package gr.ihu.ict.resumeinsync.service.crud.profile;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.service.crud.UserOwnedCrudService;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ProfileCrudService extends UserOwnedCrudService<Profile> {

    @PreAuthorize("hasPermission(#user, " +
            "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
    Try<Profile> getByUser(final User user);

    Try<List<Profile>> findAllPublished();
}

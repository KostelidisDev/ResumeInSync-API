package gr.ihu.ict.resumeinsync.domain.repository.system;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;

import java.util.List;

public interface ProfileRepository extends UserOwnedRepository<Profile> {

    List<Profile> getAllByPublishedTrue();
}

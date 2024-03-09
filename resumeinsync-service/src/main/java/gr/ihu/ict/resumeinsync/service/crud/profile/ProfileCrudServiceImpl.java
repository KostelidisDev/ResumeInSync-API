package gr.ihu.ict.resumeinsync.service.crud.profile;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.ProfileRepository;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ProfileCrudServiceImpl implements ProfileCrudService {

    private final ProfileRepository profileRepository;

    public ProfileCrudServiceImpl(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserOwnedRepository<Profile> getRepository() {
        return profileRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Profile> entityClass() {
        return Profile.class;
    }

    @Override
    public Try<Profile> getByUser(final User user) {
        return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                .flatMap(ignored -> Try.of(() -> profileRepository.getAllByUser(user)))
                .map(List::ofAll)
                .flatMap(profiles -> Option.of(profiles.get(0)).toTry());
    }

    @Override
    public Try<List<Profile>> findAllPublished() {
        return Try.of(profileRepository::getAllByPublishedTrue)
                .map(List::ofAll);
    }
}

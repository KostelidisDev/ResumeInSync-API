package gr.ihu.ict.resumeinsync.service.rpc.profile;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProfileRpcServiceImpl implements ProfileRpcService {

    private final ProfileCrudService profileCrudService;

    public ProfileRpcServiceImpl(ProfileCrudService profileCrudService) {
        this.profileCrudService = profileCrudService;
    }

    @Override
    public Try<Profile> publish(final User user) {
        return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                .flatMap(ignored -> Try.success(Boolean.TRUE))
                .flatMap(publishStatus -> setPublishStatus(user, publishStatus));
    }

    @Override
    public Try<Profile> unpublish(final User user) {
        return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                .flatMap(ignored -> Try.success(Boolean.FALSE))
                .flatMap(publishStatus -> setPublishStatus(user, publishStatus));
    }

    private Try<Profile> setPublishStatus(final User user, final Boolean publishStatus) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(publishStatus, "publishStatus is null");
        }).flatMap(ignored -> profileCrudService.getByUser(user))
                .map(profile -> {
                    profile.setPublished(publishStatus);
                    return profile;
                })
                .flatMap(profile -> Try.of(profileCrudService::getRepository)
                        .flatMap(repository -> update(repository, profile)));
    }

    private Try<Profile> update(final UserOwnedRepository<Profile> profileRepository, final Profile profile) {
        return Try.of(() -> {
            if(profileRepository == null) {
                return null;
            }
            if(profile == null) {
                return null;
            }
            return profileRepository.save(profile);
        });
    }
}

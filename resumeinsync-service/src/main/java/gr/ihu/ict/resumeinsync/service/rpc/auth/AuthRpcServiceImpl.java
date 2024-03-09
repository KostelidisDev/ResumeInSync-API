package gr.ihu.ict.resumeinsync.service.rpc.auth;

import gr.ihu.ict.resumeinsync.common.exceptions.InvalidPasswordException;
import gr.ihu.ict.resumeinsync.common.util.BooleanUtils;
import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.security.service.PasswordService;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import gr.ihu.ict.resumeinsync.service.crud.role.RoleCrudService;
import gr.ihu.ict.resumeinsync.service.crud.user.UserCrudService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class AuthRpcServiceImpl implements AuthRpcService {

    final AuthenticationManager authenticationManager;
    private final UserCrudService userCrudService;
    private final RoleCrudService roleCrudService;
    private final PasswordService passwordService;
    private final ProfileCrudService profileCrudService;

    public AuthRpcServiceImpl(final UserCrudService userCrudService,
            final RoleCrudService roleCrudService,
            final PasswordService passwordService,
            final ProfileCrudService profileCrudService,
            final AuthenticationManager authenticationManager) {
        this.userCrudService = userCrudService;
        this.roleCrudService = roleCrudService;
        this.passwordService = passwordService;
        this.profileCrudService = profileCrudService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Try<User> register(final User user,
            final Profile profile) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(profile, "profile is null");
        }).flatMap(ignored -> Option.of(user.getPassword()).toTry())
                .flatMap(plainTextPassword -> setUserDefaults(user)
                        .flatMap(passwordService::encryptUserPassword)
                        .flatMap(userCrudService::create)
                        .flatMap(createdUser -> Option.of(user.getUsername()).toTry()
                                .flatMap(username -> AuthUtils.manualAuthentication(
                                        username,
                                        plainTextPassword,
                                        authenticationManager))
                                .flatMap(ignored -> Try.success(createdUser))))
                .map(createdUser -> {
                    profile.setUser(createdUser);
                    return profile;
                })
                .flatMap(profileCrudService::create)
                .map(AbstractUserOwnedEntity::getUser);
    }

    @Override
    public Try<User> changePassword(final User user,
            final String currentPassword,
            final String newPassword) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(currentPassword, "currentPassword is null");
            Objects.requireNonNull(newPassword, "newPassword is null");
        }).flatMap(ignored -> Option.of(user.getPassword()).toTry())
                .flatMap(hashedOldPassword -> passwordService.passwordMatches(currentPassword, hashedOldPassword))
                .map(BooleanUtils::NOT)
                .flatMap(oldPasswordNotMatch -> (oldPasswordNotMatch)
                        ? Try.failure(new InvalidPasswordException("Invalid current password"))
                        : passwordService.encryptPassword(newPassword)
                                .map(hashedNewPassword -> {
                                    user.setPassword(hashedNewPassword);
                                    return user;
                                })
                                .flatMap(user1 -> userCrudService.updateById(user, user.getId())));
    }

    private Try<User> setUserDefaults(final User user) {
        return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                .flatMap(ignored -> roleCrudService.getDefaultRole())
                .map(Collections::singleton)
                .map(defaultRoleMap -> {
                    user.setRoles(defaultRoleMap);

                    user.setEnabled(true);
                    user.setAccountNonLocked(true);
                    user.setAccountNonExpired(true);
                    user.setCredentialsNonExpired(true);

                    return user;
                });
    }
}

package gr.ihu.ict.resumeinsync.security.util;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.security.domain.OAuth2AuthenticationUser;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public abstract class AuthUtils {

    public static Try<User> getAuthenticatedUser() {
        return Option.of(SecurityContextHolder.getContext()).toTry()
                .map(SecurityContext::getAuthentication)
                .flatMap(AuthUtils::resolveUserFromAuthentication);
    }

    public static Try<User> resolveUserFromAuthentication(final Authentication authentication) {

        return resolverUserDetailsFromAuthentication(authentication)
                .flatMap(AuthUtils::resolveUserFromUserDetails);
    }

    public static Try<UserDetails> resolverUserDetailsFromAuthentication(final Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return authenticationToUserDetails(authentication);
        }

        if (authentication instanceof OAuth2AuthenticationUser) {
            return authenticationToOAuth2AuthenticationUser(authentication)
                    .map(OAuth2AuthenticationUser::getUserDetails);
        }

        return Try.failure(new Exception("Can't resolve the user details."));
    }

    private static Try<UserDetails> authenticationToUserDetails(final Authentication authentication) {
        return Try.of(authentication::getPrincipal)
                .flatMap(principal -> Try.of(() -> (UserDetails) principal));
    }

    private static Try<OAuth2AuthenticationUser> authenticationToOAuth2AuthenticationUser(
            final Authentication authentication) {
        return Try.of(() -> (OAuth2AuthenticationUser) authentication);
    }

    public static Try<User> resolveUserFromUserDetails(final UserDetails userDetails) {
        return Try.of(User::new)
                .flatMap(user -> Try.of(() -> {
                    if(user == null) {
                        return user;
                    }
                    if(userDetails == null) {
                        return user;
                    }
                    BeanUtils.copyProperties(userDetails, user);
                    return user;
                }));
    }

    public static Try<Void> manualAuthentication(final String username,
            final String password,
            final AuthenticationManager authenticationManager) {
        return Try.run(() -> {
            Objects.requireNonNull(username, "username is null");
            Objects.requireNonNull(password, "password is null");
            Objects.requireNonNull(authenticationManager, "authenticationManager is null");
        }).map(aVoid -> new UsernamePasswordAuthenticationToken(username, password))
                .map(authenticationManager::authenticate)
                .flatMap(authentication -> Try.of(SecurityContextHolder::getContext)
                        .flatMap(securityContext -> Try.run(() -> securityContext.setAuthentication(authentication))));
    }
}

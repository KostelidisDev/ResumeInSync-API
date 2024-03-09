package gr.ihu.ict.resumeinsync.security.config;

import gr.ihu.ict.resumeinsync.security.SecurityProperties;
import gr.ihu.ict.resumeinsync.security.domain.OAuth2AuthenticationUser;
import gr.ihu.ict.resumeinsync.security.service.CustomUserDetailsService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

@Configuration
public class JwtAccessTokenConverterConfig {

    private final SecurityProperties securityProperties;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAccessTokenConverterConfig(final SecurityProperties securityProperties,
            final CustomUserDetailsService customUserDetailsService) {
        this.securityProperties = securityProperties;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return Option.of(securityProperties.getSigningKey())
                .toTry()
                .flatMap(signKey -> Option.of(securityProperties.getVerifierKey()).toTry()
                        .flatMap(verifyKey -> Try.of(this::getDefaultAccessTokenConverter)
                                .flatMap(defaultAccessTokenConverter -> Try.of(JwtAccessTokenConverter::new)
                                        .map(jwtAccessTokenConverter -> {
                                            jwtAccessTokenConverter.setSigningKey(signKey);
                                            jwtAccessTokenConverter.setVerifierKey(verifyKey);
                                            jwtAccessTokenConverter
                                                    .setAccessTokenConverter(defaultAccessTokenConverter);

                                            return jwtAccessTokenConverter;
                                        }))))
                .get();
    }

    private DefaultAccessTokenConverter getDefaultAccessTokenConverter() {
        return new DefaultAccessTokenConverter() {
            @Override
            public OAuth2Authentication extractAuthentication(final Map<String, ?> map) {
                return Try.of(() -> super.extractAuthentication(map))
                        .flatMap(oAuth2Authentication -> constructOAuth2AuthenticationUser(oAuth2Authentication)
                                .flatMap(oAuth2AuthenticationUser -> getUsernameFromOAuth2Authentication(
                                        oAuth2Authentication)
                                        .flatMap(username -> updateUserDetails(oAuth2AuthenticationUser, username))))
                        .get();
            }
        };
    }

    private Try<OAuth2AuthenticationUser> updateUserDetails(final OAuth2AuthenticationUser oAuth2AuthenticationUser,
            final String username) {
        return Try.of(() -> customUserDetailsService.loadUserByUsername(username))
                .map(userDetails -> {
                    oAuth2AuthenticationUser.setUserDetails(userDetails);
                    return oAuth2AuthenticationUser;
                });
    }

    private Try<OAuth2AuthenticationUser> constructOAuth2AuthenticationUser(
            final OAuth2Authentication oAuth2Authentication) {
        return Option.of(oAuth2Authentication.getOAuth2Request()).toTry()
                .flatMap(oAuth2Request -> Option.of(oAuth2Authentication.getUserAuthentication()).toTry()
                        .map(authentication -> new OAuth2AuthenticationUser(
                                oAuth2Request,
                                authentication)));
    }

    private Try<String> getUsernameFromOAuth2Authentication(final OAuth2Authentication oAuth2Authentication) {
        return Option.of(oAuth2Authentication.getUserAuthentication()).toTry()
                .flatMap(authentication -> Option.of(authentication.getName()).toTry());
    }
}

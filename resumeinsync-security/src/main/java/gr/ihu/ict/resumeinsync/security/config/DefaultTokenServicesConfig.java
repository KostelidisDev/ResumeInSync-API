package gr.ihu.ict.resumeinsync.security.config;

import io.vavr.control.Try;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class DefaultTokenServicesConfig {

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices(final TokenStore tokenStore) {
        return Try.of(DefaultTokenServices::new)
                .map(defaultTokenServices -> {
                    defaultTokenServices.setTokenStore(tokenStore);
                    defaultTokenServices.setSupportRefreshToken(Boolean.TRUE);
                    return defaultTokenServices;
                })
                .get();

    }
}

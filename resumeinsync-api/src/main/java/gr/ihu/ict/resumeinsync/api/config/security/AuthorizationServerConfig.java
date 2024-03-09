package gr.ihu.ict.resumeinsync.api.config.security;

import gr.ihu.ict.resumeinsync.common.constants.security.oauth.OAuthGrantTypes;
import gr.ihu.ict.resumeinsync.common.constants.security.oauth.OAuthScopes;
import gr.ihu.ict.resumeinsync.security.SecurityProperties;
import gr.ihu.ict.resumeinsync.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;
import java.util.Objects;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        private final SecurityProperties securityProperties;
        private final TokenStore tokenStore;
        private final JwtAccessTokenConverter jwtAccessTokenConverter;
        private final TokenEnhancerChain tokenEnhancerChain;
        private final AuthenticationManager authenticationManager;
        private final CustomUserDetailsService customUserDetailsService;
        private final PasswordEncoder passwordEncoder;

        public AuthorizationServerConfig(final SecurityProperties securityProperties,
                        final TokenStore tokenStore,
                        final TokenEnhancerChain tokenEnhancerChain,
                        final JwtAccessTokenConverter jwtAccessTokenConverter,
                        final AuthenticationManager authenticationManager,
                        final CustomUserDetailsService customUserDetailsService,
                        final PasswordEncoder passwordEncoder) {
                this.securityProperties = securityProperties;
                this.tokenStore = tokenStore;
                this.tokenEnhancerChain = tokenEnhancerChain;
                this.jwtAccessTokenConverter = jwtAccessTokenConverter;
                this.authenticationManager = authenticationManager;
                this.customUserDetailsService = customUserDetailsService;
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void configure(final ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
                Objects.requireNonNull(clientDetailsServiceConfigurer, "clientDetailsServiceConfigurer is null");

                final String clientId = securityProperties.getClientId();
                final String clientSecret = securityProperties.getClientSecret();
                final String[] grantTypes = new String[] {
                                OAuthGrantTypes.PASSWORD,
                                OAuthGrantTypes.REFRESH_TOKEN
                };
                final String[] scopes = new String[] {
                                OAuthScopes.READ,
                                OAuthScopes.WRITE,
                                OAuthScopes.TRUST
                };
                final String resourceId = securityProperties.getResourceId();
                final Integer accessTokenDuration = securityProperties.getAccessTokenDuration();
                final Integer refreshTokenDuration = securityProperties.getRefreshTokenDuration();

                clientDetailsServiceConfigurer
                                .inMemory()
                                .withClient(clientId)
                                .secret(clientSecret)
                                .authorizedGrantTypes(grantTypes)
                                .scopes(scopes)
                                .resourceIds(resourceId)
                                .accessTokenValiditySeconds(accessTokenDuration)
                                .refreshTokenValiditySeconds(refreshTokenDuration);
        }

        @Override
        public void configure(final AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) {
                Objects.requireNonNull(authorizationServerEndpointsConfigurer,
                                "authorizationServerEndpointsConfigurer is null");

                tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));

                authorizationServerEndpointsConfigurer.tokenStore(tokenStore)
                                .accessTokenConverter(jwtAccessTokenConverter)
                                .tokenEnhancer(tokenEnhancerChain)
                                .authenticationManager(authenticationManager)
                                .userDetailsService(customUserDetailsService);
        }

        @Override
        @Primary
        public void configure(final AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) {
                Objects.requireNonNull(authorizationServerSecurityConfigurer,
                                "authorizationServerSecurityConfigurer is null");

                authorizationServerSecurityConfigurer.passwordEncoder(passwordEncoder)
                                .allowFormAuthenticationForClients();
        }
}

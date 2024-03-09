package gr.ihu.ict.resumeinsync.api.config.security;

import gr.ihu.ict.resumeinsync.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import java.util.Objects;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final SecurityProperties securityProperties;

    public ResourceServerConfig(final SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) {
        Objects.requireNonNull(resourceServerSecurityConfigurer, "resourceServerSecurityConfigurer is null");

        resourceServerSecurityConfigurer
                .resourceId(securityProperties.getResourceId())
                .stateless(Boolean.TRUE);
    }

    @Override
    public void configure(final HttpSecurity httpSecurity) throws Exception {
        Objects.requireNonNull(httpSecurity, "httpSecurity is null");

        httpSecurity
                .authorizeRequests(requests -> requests
                        .antMatchers("/").permitAll()
                        .antMatchers("/rpc/auth/register").permitAll()
                        .antMatchers("/rpc/resume/upload/**").authenticated()
                        .antMatchers("/rpc/resume/**").permitAll()
                        .antMatchers("/rpc/profile/published").permitAll()
                        .antMatchers("/**").authenticated())
                .exceptionHandling(handling -> handling.accessDeniedHandler(new OAuth2AccessDeniedHandler()));
    }
}

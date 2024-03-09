package gr.ihu.ict.resumeinsync.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import io.vavr.control.Try;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        Objects.requireNonNull(httpSecurity, "httpSecurity is null");

        httpSecurity.csrf(csrf -> Try.of(() -> csrf.disable())
                .map(disable -> Try.of(() -> disable.anonymous(anonymous -> {
                    anonymous.disable();
                }))));
    }
}

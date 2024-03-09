package gr.ihu.ict.resumeinsync.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class CorsConfigurationConfig {

    @Bean
    public CorsConfiguration corsConfiguration() {
        return new CorsConfiguration();
    }
}

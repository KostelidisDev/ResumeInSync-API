package gr.ihu.ict.resumeinsync.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class UrlBasedCorsConfigurationSourceConfig {

    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        return new UrlBasedCorsConfigurationSource();
    }
}

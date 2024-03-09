package gr.ihu.ict.resumeinsync.security;

import gr.ihu.ict.resumeinsync.domain.DomainConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        DomainConfiguration.class,
})
@ComponentScan
@Configuration
public class SecurityConfiguration {
}

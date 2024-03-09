package gr.ihu.ict.resumeinsync.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EntityScan
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class DomainConfiguration {
}

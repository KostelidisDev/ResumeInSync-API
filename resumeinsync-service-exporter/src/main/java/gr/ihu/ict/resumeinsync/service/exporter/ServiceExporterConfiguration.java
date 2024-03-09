package gr.ihu.ict.resumeinsync.service.exporter;

import gr.ihu.ict.resumeinsync.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        SecurityConfiguration.class
})
@ComponentScan
@Configuration
public class ServiceExporterConfiguration {
}

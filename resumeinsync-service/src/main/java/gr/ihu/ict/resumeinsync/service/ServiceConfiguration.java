package gr.ihu.ict.resumeinsync.service;

import gr.ihu.ict.resumeinsync.security.SecurityConfiguration;
import gr.ihu.ict.resumeinsync.service.exporter.ServiceExporterConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        SecurityConfiguration.class,
        ServiceExporterConfiguration.class
})
@ComponentScan
@Configuration
public class ServiceConfiguration {
}

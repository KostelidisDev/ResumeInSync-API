package gr.ihu.ict.resumeinsync.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.ihu.ict.linkedin.data.importer.service.LinkedInDataImporter;
import gr.ihu.ict.linkedin.data.importer.service.LinkedInDataImporterImpl;

@Configuration
public class LinkedInDataImporterConfig {
    @Bean
    LinkedInDataImporter linkedArchiveParser() {
        return new LinkedInDataImporterImpl();
    }
}

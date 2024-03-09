package gr.ihu.ict.resumeinsync.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.ihu.ict.zotero.publications.importer.config.DefaultConfig;
import gr.ihu.ict.zotero.publications.importer.service.PublicationItemService;
import gr.ihu.ict.zotero.publications.importer.service.PublicationItemServiceImpl;

@Configuration
public class ZoteroPublicationsImporterConfig {
    @Bean
    DefaultConfig defaultConfig() {
        return new DefaultConfig();
    }

    @Bean
    PublicationItemService publicationItemService(DefaultConfig defaultConfig) {
        return new PublicationItemServiceImpl(defaultConfig);
    }
}

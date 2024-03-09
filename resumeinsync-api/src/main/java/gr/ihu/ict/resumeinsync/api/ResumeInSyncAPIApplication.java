package gr.ihu.ict.resumeinsync.api;

import gr.ihu.ict.resumeinsync.domain.DomainConfiguration;
import gr.ihu.ict.resumeinsync.mapper.MapperConfiguration;
import gr.ihu.ict.resumeinsync.security.SecurityConfiguration;
import gr.ihu.ict.resumeinsync.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Import({
        DomainConfiguration.class,
        SecurityConfiguration.class,
        ServiceConfiguration.class,
        MapperConfiguration.class,
})
@SpringBootApplication
public class ResumeInSyncAPIApplication extends SpringBootServletInitializer {

    public static void main(final String... args) {
        SpringApplication.run(ResumeInSyncAPIApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ResumeInSyncAPIApplication.class);
    }

    @RestController
    @RequestMapping("/")
    public static class IndexController {
        @GetMapping
        public void index(final HttpServletResponse httpServletResponse) {
            httpServletResponse.setHeader("Location", "https://resumeinsync.kostelidis.dev");
            httpServletResponse.setStatus(302);
        }
    }
}

package gr.ihu.ict.resumeinsync.service.exporter.config;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import io.vavr.control.Try;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlebarsConfig {

    @Bean
    public Handlebars handlebars() {
        return Try.of(() -> new ClassPathTemplateLoader("/handlebars/resume", ".hbs"))
                .flatMap(classPathTemplateLoader -> Try.of(() -> new Handlebars(classPathTemplateLoader)))
                .flatMap(handlebars -> Try.of(() -> {
                    handlebars.registerHelpers(HandlebarsHelpersConfig.class);
                    return handlebars;
                }))
                .get();
    }
}

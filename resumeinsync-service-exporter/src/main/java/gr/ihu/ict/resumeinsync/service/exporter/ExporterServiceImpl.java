package gr.ihu.ict.resumeinsync.service.exporter;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.html2pdf.HtmlConverter;
import gr.ihu.ict.resumeinsync.domain.model.resume.Resume;
import io.vavr.control.Try;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public class ExporterServiceImpl implements ExporterService {

    private final Handlebars handlebars;
    private final String htmlTemplate = "default";
    private final String pdfExtension = ".pdf";

    public ExporterServiceImpl(final Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    @Override
    public Try<String> exportHtml(final Resume resume) {
        return Try.run(() -> Objects.requireNonNull(resume, "resume is null"))
                .map(ignored -> htmlTemplate)
                .flatMap(templateFile -> Try.of(() -> handlebars.compile(templateFile)))
                .flatMap(template -> Try.of(() -> template.apply(resume)));
    }

    @Override
    public Try<Resource> exportPdf(final Resume resume) {
        return Try.run(() -> Objects.requireNonNull(resume, "resume is null"))
                .flatMap(ignored -> exportHtml(resume))
                .flatMap(html -> getTempFileName(pdfExtension)
                        .flatMap(file -> Try.of(() -> new FileOutputStream(file))
                                .flatMap(fileOutputStream -> convertToPdf(html, fileOutputStream)
                                        .map(ignored -> file))))
                .flatMap(this::fileToFileSystemResource);
    }

    private Try<FileSystemResource> fileToFileSystemResource(final File file) {
        return Try.of(() -> {
            if(file == null) {
                return null;
            }
            return new FileSystemResource(file);
        });
    }

    private Try<Void> convertToPdf(final String html, final FileOutputStream fileOutputStream) {
        return Try.run(() -> {
            Objects.requireNonNull(html, "html is null");
            Objects.requireNonNull(fileOutputStream, "fileOutputStream is null");
        }).flatMap(unused -> Try.run(() -> HtmlConverter.convertToPdf(html, fileOutputStream)));
    }

    private Try<File> getTempFileName(final String suffix) {
        return Try.run(() -> Objects.requireNonNull(suffix, "suffix is null"))
                .flatMap(ignored -> Try.of(UUID::randomUUID))
                .map(UUID::toString)
                .flatMap(uuid -> Try.of(() -> File.createTempFile(uuid, suffix)));
    }
}

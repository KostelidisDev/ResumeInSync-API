package gr.ihu.ict.resumeinsync.service.exporter;

import gr.ihu.ict.resumeinsync.domain.model.resume.Resume;
import io.vavr.control.Try;
import org.springframework.core.io.Resource;

public interface ExporterService {
    Try<String> exportHtml(Resume resume);

    Try<Resource> exportPdf(Resume resume);
}

package gr.ihu.ict.resumeinsync.domain.model.resume.metadata;

import gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language.ResumeLanguageMetadata;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResumeMetadata {
    private ResumeLanguageMetadata language;
    private Date generationDate;
}

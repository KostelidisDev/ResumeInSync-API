package gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language;

import lombok.Getter;

@Getter
public class ResumeEnglishLanguageMetadata extends ResumeLanguageMetadata {
    public ResumeEnglishLanguageMetadata() {
        super(
                "en",
                "CV",
                "Description",
                "Currently",
                "Professional experiences",
                "Volunteer experiences",
                "Educations",
                "Certifications",
                "Skills",
                "Languages",
                "Publications",
                "Title",
                "Authors",
                "Publisher",
                "URL");
    }
}

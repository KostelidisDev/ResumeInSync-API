package gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ResumeLanguageMetadata {
    private final String language;
    private final String title;
    private final String descriptionTitle;
    private final String currentlyTitle;
    private final String professionalExperiencesTitle;
    private final String volunteerExperiencesTitle;
    private final String educationsTitle;
    private final String certificationsTitle;
    private final String skillsTitle;
    private final String languagesTitle;
    private final String publicationsTitle;
    private final String publicationTitle;
    private final String publicationAuthors;
    private final String publicationPublisher;
    private final String publicationUrl;
}

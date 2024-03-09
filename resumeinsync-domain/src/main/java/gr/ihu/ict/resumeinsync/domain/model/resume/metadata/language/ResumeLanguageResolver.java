package gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language;

import gr.ihu.ict.resumeinsync.common.constants.SystemLanguage;

public abstract class ResumeLanguageResolver {
    public static ResumeLanguageMetadata resolve(final SystemLanguage systemLanguage) {
        switch (systemLanguage) {
            case GREEK:
                return new ResumeGreekLanguageMetadata();
            case ENGLISH:
            default:
                return new ResumeEnglishLanguageMetadata();
        }
    }
}

package gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language;

import lombok.Getter;

@Getter
public class ResumeGreekLanguageMetadata extends ResumeLanguageMetadata {
    public ResumeGreekLanguageMetadata() {
        super(
                "el",
                "Βιογραφικό",
                "Περιγραφή",
                "Σήμερα",
                "Επαγγελματικές εμπειρίες",
                "Εθελοντικές εμπειρίες",
                "Εκπαίδευση",
                "Πιστοποιητικά",
                "Ικανότητες",
                "Γλώσσες",
                "Δημοσιεύσεις",
                "Τίτλος",
                "Συγγραφείς",
                "Εκδότης",
                "URL");
    }
}

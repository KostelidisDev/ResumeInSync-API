package gr.ihu.ict.resumeinsync.domain.model.resume;

import gr.ihu.ict.resumeinsync.domain.entity.system.*;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.ProfessionalExperience;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.domain.model.resume.metadata.ResumeMetadata;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Resume {
    private ResumeMetadata metadata;

    private User user;
    private Profile profile;

    private List<ProfessionalExperience> professionalExperiences;
    private List<VolunteerExperience> volunteerExperiences;
    private List<Education> educations;
    private List<Certification> certifications;
    private List<Skill> skills;
    private List<Language> languages;
    private List<Publication> publications;
}

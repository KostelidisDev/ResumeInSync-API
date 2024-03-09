package gr.ihu.ict.resumeinsync.dto.response;

import gr.ihu.ict.resumeinsync.dto.system.output.*;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.ProfessionalExperienceOutputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.VolunteerExperienceOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeDTO {
    private ResumeMetadataDTO metadata;

    private UserOutputDTO user;
    private ProfileOutputDTO profile;

    private List<ProfessionalExperienceOutputDTO> professionalExperiences;
    private List<VolunteerExperienceOutputDTO> volunteerExperiences;
    private List<EducationOutputDTO> educations;
    private List<CertificationOutputDTO> certifications;
    private List<SkillOutputDTO> skills;
    private List<LanguageOutputDTO> languages;
    private List<PublicationOutputDTO> publications;
}

package gr.ihu.ict.resumeinsync.mapper.other;

import gr.ihu.ict.resumeinsync.domain.model.resume.Resume;
import gr.ihu.ict.resumeinsync.dto.Reference;
import gr.ihu.ict.resumeinsync.dto.response.ResumeDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.*;
import gr.ihu.ict.resumeinsync.mapper.system.output.experience.ProfessionalExperienceOutputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.experience.VolunteerExperienceOutputMapper;

@org.mapstruct.Mapper(uses = {
        Reference.class,
        ResumeMetadataMapper.class,
        UserOutputMapper.class,
        ProfileOutputMapper.class,
        ProfessionalExperienceOutputMapper.class,
        VolunteerExperienceOutputMapper.class,
        EducationOutputMapper.class,
        CertificationsOutputMapper.class,
        SkillOutputMapper.class,
        LanguageOutputMapper.class,
        PublicationOutputMapper.class
})
public interface ResumeMapper extends Mapper<Resume, ResumeDTO> {
}

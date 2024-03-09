package gr.ihu.ict.resumeinsync.mapper.system.output.experience;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.dto.system.output.experience.VolunteerExperienceOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface VolunteerExperienceOutputMapper extends Mapper<VolunteerExperience, VolunteerExperienceOutputDTO> {
}

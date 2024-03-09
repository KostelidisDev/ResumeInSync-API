package gr.ihu.ict.resumeinsync.mapper.system.input.experience;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.dto.system.input.exprerience.VolunteerExperienceInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface VolunteerExperienceInputMapper extends Mapper<VolunteerExperience, VolunteerExperienceInputDTO> {
}

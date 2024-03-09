package gr.ihu.ict.resumeinsync.mapper.system.output;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface ProfileOutputMapper extends Mapper<Profile, ProfileOutputDTO> {
}

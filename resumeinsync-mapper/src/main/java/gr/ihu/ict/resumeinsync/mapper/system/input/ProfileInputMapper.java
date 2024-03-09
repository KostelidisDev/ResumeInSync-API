package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.dto.system.input.ProfileInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface ProfileInputMapper extends Mapper<Profile, ProfileInputDTO> {
}

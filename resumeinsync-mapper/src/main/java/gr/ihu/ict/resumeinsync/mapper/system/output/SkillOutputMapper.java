package gr.ihu.ict.resumeinsync.mapper.system.output;

import gr.ihu.ict.resumeinsync.domain.entity.system.Skill;
import gr.ihu.ict.resumeinsync.dto.system.output.SkillOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface SkillOutputMapper extends Mapper<Skill, SkillOutputDTO> {
}

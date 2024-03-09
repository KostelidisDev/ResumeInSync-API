package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.Skill;
import gr.ihu.ict.resumeinsync.dto.system.input.SkillInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface SkillInputMapper extends Mapper<Skill, SkillInputDTO> {
}

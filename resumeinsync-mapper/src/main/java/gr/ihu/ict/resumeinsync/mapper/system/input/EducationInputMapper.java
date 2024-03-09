package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.Education;
import gr.ihu.ict.resumeinsync.dto.system.input.EducationInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface EducationInputMapper extends Mapper<Education, EducationInputDTO> {
}

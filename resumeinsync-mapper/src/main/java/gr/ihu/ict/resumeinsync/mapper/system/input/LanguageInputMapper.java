package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.Language;
import gr.ihu.ict.resumeinsync.dto.system.input.LanguageInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface LanguageInputMapper extends Mapper<Language, LanguageInputDTO> {
}

package gr.ihu.ict.resumeinsync.mapper.system.output;

import gr.ihu.ict.resumeinsync.domain.entity.system.Publication;
import gr.ihu.ict.resumeinsync.dto.system.output.PublicationOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface PublicationOutputMapper extends Mapper<Publication, PublicationOutputDTO> {
}

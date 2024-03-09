package gr.ihu.ict.resumeinsync.mapper.system.output;

import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import gr.ihu.ict.resumeinsync.dto.system.output.CertificationOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface CertificationsOutputMapper extends Mapper<Certification, CertificationOutputDTO> {
}

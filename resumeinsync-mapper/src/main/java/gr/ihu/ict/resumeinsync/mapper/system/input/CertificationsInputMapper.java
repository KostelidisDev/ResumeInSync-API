package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import gr.ihu.ict.resumeinsync.dto.system.input.CertificationInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class
})
public interface CertificationsInputMapper extends Mapper<Certification, CertificationInputDTO> {
}

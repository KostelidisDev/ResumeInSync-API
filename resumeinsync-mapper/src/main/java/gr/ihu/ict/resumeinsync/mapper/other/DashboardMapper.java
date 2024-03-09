package gr.ihu.ict.resumeinsync.mapper.other;

import gr.ihu.ict.resumeinsync.domain.model.Dashboard;
import gr.ihu.ict.resumeinsync.dto.response.DashboardDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.ProfileOutputMapper;

@org.mapstruct.Mapper(uses = {
        ReferenceMapper.class,
        ProfileOutputMapper.class,
        RecordCountsMapper.class
})
public interface DashboardMapper extends Mapper<Dashboard, DashboardDTO> {
}

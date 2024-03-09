package gr.ihu.ict.resumeinsync.mapper.other;

import gr.ihu.ict.resumeinsync.domain.model.RecordCounts;
import gr.ihu.ict.resumeinsync.dto.response.RecordCountsResponse;
import gr.ihu.ict.resumeinsync.mapper.Mapper;

@org.mapstruct.Mapper
public interface RecordCountsMapper extends Mapper<RecordCounts, RecordCountsResponse> {
}

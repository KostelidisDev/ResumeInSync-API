package gr.ihu.ict.resumeinsync.dto.response;

import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardDTO {
    private ProfileOutputDTO profile;
    private RecordCountsResponse recordCounts;
}

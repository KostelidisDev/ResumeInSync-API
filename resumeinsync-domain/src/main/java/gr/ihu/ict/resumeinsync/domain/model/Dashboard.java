package gr.ihu.ict.resumeinsync.domain.model;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Dashboard {
    private Profile profile;
    private RecordCounts recordCounts;
}

package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificationOutputDTO extends AbstractUserOwnedEntityDTO {

    private String name;

    private String organization;

    private Boolean canExpire;

    private DateRangeDTO dateRange;

    private String originalId;

    private String originalUrl;
}

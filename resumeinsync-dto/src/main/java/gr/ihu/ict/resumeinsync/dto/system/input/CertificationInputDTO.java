package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import gr.ihu.ict.resumeinsync.dto.embeddable.DateRangeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CertificationInputDTO extends AbstractUserOwnedEntityDTO {

    @NotNull
    @Length(min = 1, max = 40)
    private String name;

    @NotNull
    @Length(min = 1, max = 40)
    private String organization;

    @NotNull
    private Boolean canExpire;

    @NotNull
    private DateRangeDTO dateRange;

    @Length(max = 256)
    private String originalId;

    @Length(max = 1024)
    private String originalUrl;
}

package gr.ihu.ict.resumeinsync.dto.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class DateRangeDTO {

    @NotNull
    private Date startDate;

    private Date endDate;
}

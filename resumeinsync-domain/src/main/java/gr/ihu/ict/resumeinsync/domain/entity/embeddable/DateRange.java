package gr.ihu.ict.resumeinsync.domain.entity.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class DateRange {

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
}

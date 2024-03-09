package gr.ihu.ict.resumeinsync.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractEntityDTO {

    private String id;

    private Date createdAt;

    private Date updatedAt;
}

package gr.ihu.ict.resumeinsync.dto.system.output;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PublicationOutputDTO extends AbstractUserOwnedEntityDTO {

    private String title;

    private String publisher;

    private Date publicationDate;

    private String authors;

    private String url;

    private String description;
}

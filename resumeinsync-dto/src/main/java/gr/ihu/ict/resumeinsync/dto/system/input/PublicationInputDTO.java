package gr.ihu.ict.resumeinsync.dto.system.input;

import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PublicationInputDTO extends AbstractUserOwnedEntityDTO {

    @NotNull
    @Length(min = 1, max = 128)
    private String title;

    @NotNull
    @Length(min = 1, max = 128)
    private String publisher;

    @NotNull
    private Date publicationDate;

    @Length(min = 1, max = 1024)
    private String authors;

    @Length(max = 1024)
    private String url;

    @Length(max = 10240)
    private String description;
}

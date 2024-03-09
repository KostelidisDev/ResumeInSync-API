package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Publication")
@Table(name = "publications")
public class Publication extends AbstractUserOwnedEntity {

    @Column
    private String title;

    @Column
    private String publisher;

    @Column
    private Date publicationDate;

    @Column
    private String authors;

    @Column
    private String url;

    @Column
    private String description;
}

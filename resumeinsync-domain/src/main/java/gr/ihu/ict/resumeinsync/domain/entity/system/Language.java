package gr.ihu.ict.resumeinsync.domain.entity.system;

import gr.ihu.ict.resumeinsync.common.constants.LanguageProficiency;
import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Language")
@Table(name = "languages")
public class Language extends AbstractUserOwnedEntity {

    @Column
    private String name;

    @Column
    private LanguageProficiency proficiency;
}

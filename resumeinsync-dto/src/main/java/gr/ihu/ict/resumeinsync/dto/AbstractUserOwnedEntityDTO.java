package gr.ihu.ict.resumeinsync.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class AbstractUserOwnedEntityDTO extends AbstractEntityDTO {

    @NotNull
    private Reference user;
    
    private String importSource;
}

package gr.ihu.ict.resumeinsync.dto.request;

import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportZoteroDto {
    @NotNull
    private String userId;
}

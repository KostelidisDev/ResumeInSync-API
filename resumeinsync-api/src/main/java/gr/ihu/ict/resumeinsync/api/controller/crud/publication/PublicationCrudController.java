package gr.ihu.ict.resumeinsync.api.controller.crud.publication;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Publication;
import gr.ihu.ict.resumeinsync.dto.system.input.PublicationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.PublicationOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publications")
public interface PublicationCrudController
                extends UserOwnedCrudController<PublicationInputDTO, PublicationOutputDTO, Publication> {
}

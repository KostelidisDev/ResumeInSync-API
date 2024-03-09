package gr.ihu.ict.resumeinsync.api.controller.crud;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.AbstractUserOwnedEntityDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserOwnedCrudController<INPUT_TYPE extends AbstractUserOwnedEntityDTO, OUTPUT_TYPE extends AbstractUserOwnedEntityDTO, ENTITY_TYPE extends AbstractUserOwnedEntity>
                extends
                CrudController<INPUT_TYPE, OUTPUT_TYPE, ENTITY_TYPE, UserOwnedRepository<ENTITY_TYPE>> {
}

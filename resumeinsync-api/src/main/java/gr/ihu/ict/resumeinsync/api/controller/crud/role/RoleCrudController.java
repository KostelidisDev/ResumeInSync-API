package gr.ihu.ict.resumeinsync.api.controller.crud.role;

import gr.ihu.ict.resumeinsync.api.controller.crud.CrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.dto.system.input.RoleInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.RoleOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public interface RoleCrudController
                extends CrudController<RoleInputDTO, RoleOutputDTO, Role, Repository<Role>> {
}

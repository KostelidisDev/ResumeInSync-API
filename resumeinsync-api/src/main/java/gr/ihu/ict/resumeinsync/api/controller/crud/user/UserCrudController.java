package gr.ihu.ict.resumeinsync.api.controller.crud.user;

import gr.ihu.ict.resumeinsync.api.controller.crud.CrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.dto.system.input.UserInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public interface UserCrudController
                extends CrudController<UserInputDTO, UserOutputDTO, User, Repository<User>> {
}

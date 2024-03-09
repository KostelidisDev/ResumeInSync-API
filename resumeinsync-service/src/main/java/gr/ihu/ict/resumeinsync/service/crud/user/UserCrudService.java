package gr.ihu.ict.resumeinsync.service.crud.user;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;

public interface UserCrudService extends CrudService<User, Repository<User>> {
}

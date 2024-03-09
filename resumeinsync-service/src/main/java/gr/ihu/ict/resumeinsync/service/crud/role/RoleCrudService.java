package gr.ihu.ict.resumeinsync.service.crud.role;

import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import io.vavr.control.Try;

public interface RoleCrudService extends CrudService<Role, Repository<Role>> {

    Try<Role> findOneByName(String name);

    Try<Role> getDefaultRole();
}

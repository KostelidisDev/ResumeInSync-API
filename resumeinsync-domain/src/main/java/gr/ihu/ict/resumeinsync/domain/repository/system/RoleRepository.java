package gr.ihu.ict.resumeinsync.domain.repository.system;

import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role> {

    Optional<Role> findByName(String name);
}

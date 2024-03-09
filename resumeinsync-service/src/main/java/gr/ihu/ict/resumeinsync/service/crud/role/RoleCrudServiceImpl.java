package gr.ihu.ict.resumeinsync.service.crud.role;

import gr.ihu.ict.resumeinsync.common.constants.security.UserRole;
import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.domain.repository.system.RoleRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.UserRepository;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class RoleCrudServiceImpl implements RoleCrudService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleCrudServiceImpl(final RoleRepository roleRepository,
            final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Repository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Role> entityClass() {
        return Role.class;
    }

    @Override
    public Try<Role> findOneByName(final String name) {
        return Try.run(() -> Objects.requireNonNull(name, "name is null"))
                .map(ignored -> Option.ofOptional(roleRepository.findByName(name)))
                .flatMap(Value::toTry);
    }

    @Override
    public Try<Role> getDefaultRole() {
        return Try.of(userRepository::hasRecords)
                .map(hasUsers -> (hasUsers)
                        ? UserRole.USER
                        : UserRole.ADMIN)
                .map(UserRole::getValue)
                .flatMap(this::findOneByName);
    }
}

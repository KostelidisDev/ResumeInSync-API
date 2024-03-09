package gr.ihu.ict.resumeinsync.api.controller.crud.role;

import gr.ihu.ict.resumeinsync.domain.entity.system.Role;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.dto.system.input.RoleInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.RoleOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.RoleInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.RoleOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.role.RoleCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleCrudControllerImpl implements RoleCrudController {

    private final RoleCrudService roleCrudService;
    private final RoleInputMapper roleInputMapper;
    private final RoleOutputMapper roleOutputMapper;

    public RoleCrudControllerImpl(final RoleCrudService roleCrudService,
            final RoleInputMapper roleInputMapper,
            final RoleOutputMapper roleOutputMapper) {
        this.roleCrudService = roleCrudService;
        this.roleInputMapper = roleInputMapper;
        this.roleOutputMapper = roleOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public CrudService<Role, Repository<Role>> getCrudService() {
        return roleCrudService;
    }

    @Override
    public Mapper<Role, RoleInputDTO> getInputMapperDTO() {
        return roleInputMapper;
    }

    @Override
    public Mapper<Role, RoleOutputDTO> getOutputMapperDTO() {
        return roleOutputMapper;
    }

    @Override
    @PostMapping({ "/", "" })
    public ResponseEntity<RoleOutputDTO> create(@RequestBody @Valid final RoleInputDTO requestBody) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RoleOutputDTO> updateById(@PathVariable final String id,
            @RequestBody @Valid final RoleInputDTO requestBody) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final String id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}

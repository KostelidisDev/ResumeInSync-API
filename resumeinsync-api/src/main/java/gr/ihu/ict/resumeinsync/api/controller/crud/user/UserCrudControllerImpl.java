package gr.ihu.ict.resumeinsync.api.controller.crud.user;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.dto.system.input.UserInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.UserInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.UserOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.user.UserCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserCrudControllerImpl implements UserCrudController {

    private final UserInputMapper userInputMapper;
    private final UserOutputMapper userOutputMapper;
    private final UserCrudService userCrudService;

    public UserCrudControllerImpl(final UserInputMapper userInputMapper,
            final UserOutputMapper userOutputMapper,
            final UserCrudService userCrudService) {
        this.userInputMapper = userInputMapper;
        this.userOutputMapper = userOutputMapper;
        this.userCrudService = userCrudService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public CrudService<User, Repository<User>> getCrudService() {
        return userCrudService;
    }

    @Override
    public Mapper<User, UserInputDTO> getInputMapperDTO() {
        return userInputMapper;
    }

    @Override
    public Mapper<User, UserOutputDTO> getOutputMapperDTO() {
        return userOutputMapper;
    }

    @Override
    public ResponseEntity<UserOutputDTO> create(@Valid final UserInputDTO requestBody) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}

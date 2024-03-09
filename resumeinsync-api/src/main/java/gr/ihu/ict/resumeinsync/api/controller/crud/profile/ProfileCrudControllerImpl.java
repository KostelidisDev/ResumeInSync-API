package gr.ihu.ict.resumeinsync.api.controller.crud.profile;

import gr.ihu.ict.resumeinsync.domain.entity.system.Profile;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.dto.system.input.ProfileInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.ProfileOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.system.input.ProfileInputMapper;
import gr.ihu.ict.resumeinsync.mapper.system.output.ProfileOutputMapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
@Slf4j
public class ProfileCrudControllerImpl implements ProfileCrudController {

    private final ProfileCrudService profileCrudService;
    private final ProfileInputMapper profileInputMapper;
    private final ProfileOutputMapper profileOutputMapper;

    public ProfileCrudControllerImpl(final ProfileCrudService profileCrudService,
            final ProfileInputMapper profileInputMapper,
            final ProfileOutputMapper profileOutputMapper) {
        this.profileCrudService = profileCrudService;
        this.profileInputMapper = profileInputMapper;
        this.profileOutputMapper = profileOutputMapper;
    }

    @Override
    public CrudService<Profile, UserOwnedRepository<Profile>> getCrudService() {
        return profileCrudService;
    }

    @Override
    public Mapper<Profile, ProfileInputDTO> getInputMapperDTO() {
        return profileInputMapper;
    }

    @Override
    public Mapper<Profile, ProfileOutputDTO> getOutputMapperDTO() {
        return profileOutputMapper;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public ResponseEntity<ProfileOutputDTO> create(@Valid final ProfileInputDTO requestBody) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}

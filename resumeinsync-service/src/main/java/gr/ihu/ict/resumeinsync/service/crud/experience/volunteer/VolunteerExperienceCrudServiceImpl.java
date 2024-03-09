package gr.ihu.ict.resumeinsync.service.crud.experience.volunteer;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.VolunteerExperienceRepository;
import gr.ihu.ict.resumeinsync.service.util.ExperienceUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class VolunteerExperienceCrudServiceImpl implements VolunteerExperienceCrudService {

    private final VolunteerExperienceRepository volunteerExperienceRepository;

    public VolunteerExperienceCrudServiceImpl(final VolunteerExperienceRepository volunteerExperienceRepository) {
        this.volunteerExperienceRepository = volunteerExperienceRepository;
    }

    @Override
    public UserOwnedRepository<VolunteerExperience> getRepository() {
        return volunteerExperienceRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<VolunteerExperience> entityClass() {
        return VolunteerExperience.class;
    }

    @Override
    public Try<VolunteerExperience> create(final VolunteerExperience entity) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> ExperienceUtils.optimizeEndDate(entity))
                .flatMap(optimizedVolunteerExperience -> VolunteerExperienceCrudService.super.create(
                        optimizedVolunteerExperience));
    }

    @Override
    public Try<VolunteerExperience> updateById(final VolunteerExperience entity, final String id) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> ExperienceUtils.optimizeEndDate(entity))
                .flatMap(optimizedVolunteerExperience -> VolunteerExperienceCrudService.super.updateById(
                        optimizedVolunteerExperience, id));
    }
}

package gr.ihu.ict.resumeinsync.service.crud.experience.professional;

import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.ProfessionalExperience;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.ProfessionalExperienceRepository;
import gr.ihu.ict.resumeinsync.service.util.ExperienceUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ProfessionalExperienceCrudServiceImpl implements ProfessionalExperienceCrudService {

    private final ProfessionalExperienceRepository professionalExperienceRepository;

    public ProfessionalExperienceCrudServiceImpl(
            final ProfessionalExperienceRepository professionalExperienceRepository) {
        this.professionalExperienceRepository = professionalExperienceRepository;
    }

    @Override
    public UserOwnedRepository<ProfessionalExperience> getRepository() {
        return professionalExperienceRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<ProfessionalExperience> entityClass() {
        return ProfessionalExperience.class;
    }

    @Override
    public Try<ProfessionalExperience> create(final ProfessionalExperience entity) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> ExperienceUtils.optimizeEndDate(entity))
                .flatMap(workExperience -> ProfessionalExperienceCrudService.super.create(workExperience));
    }

    @Override
    public Try<ProfessionalExperience> updateById(final ProfessionalExperience entity,
            final String id) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> ExperienceUtils.optimizeEndDate(entity))
                .flatMap(optimizedProfessionalExperience -> ProfessionalExperienceCrudService.super.updateById(
                        optimizedProfessionalExperience, id));
    }
}

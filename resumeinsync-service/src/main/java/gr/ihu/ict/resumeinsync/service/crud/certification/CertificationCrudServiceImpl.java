package gr.ihu.ict.resumeinsync.service.crud.certification;

import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.CertificateRepository;
import gr.ihu.ict.resumeinsync.service.util.CertificationUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CertificationCrudServiceImpl implements CertificationCrudService {

    private final CertificateRepository certificateRepository;

    public CertificationCrudServiceImpl(final CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public UserOwnedRepository<Certification> getRepository() {
        return certificateRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Certification> entityClass() {
        return Certification.class;
    }

    @Override
    public Try<Certification> create(final Certification entity) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> CertificationUtils.optimizeEndDate(entity))
                .flatMap(CertificationCrudService.super::create);
    }

    @Override
    public Try<Certification> updateById(final Certification entity, final String id) {
        return Try.run(() -> Objects.requireNonNull(entity, "entity is null"))
                .flatMap(ignored -> CertificationUtils.optimizeEndDate(entity))
                .flatMap(optimizedCertification -> CertificationCrudService.super.updateById(optimizedCertification,
                        id));
    }
}

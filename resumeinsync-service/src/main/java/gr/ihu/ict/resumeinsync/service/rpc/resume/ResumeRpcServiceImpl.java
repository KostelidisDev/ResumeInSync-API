package gr.ihu.ict.resumeinsync.service.rpc.resume;

import gr.ihu.ict.linkedin.data.importer.model.csv.Position;
import gr.ihu.ict.linkedin.data.importer.service.LinkedInDataImporter;
import gr.ihu.ict.resumeinsync.common.constants.LanguageProficiency;
import gr.ihu.ict.resumeinsync.common.constants.SystemLanguage;
import gr.ihu.ict.resumeinsync.common.util.BooleanUtils;
import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.embeddable.DateRange;
import gr.ihu.ict.resumeinsync.domain.entity.system.*;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.ProfessionalExperience;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.VolunteerExperience;
import gr.ihu.ict.resumeinsync.domain.model.resume.Resume;
import gr.ihu.ict.resumeinsync.domain.model.resume.metadata.ResumeMetadata;
import gr.ihu.ict.resumeinsync.domain.model.resume.metadata.language.ResumeLanguageResolver;
import gr.ihu.ict.resumeinsync.service.crud.UserOwnedCrudService;
import gr.ihu.ict.resumeinsync.service.crud.certification.CertificationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.education.EducationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.professional.ProfessionalExperienceCrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.volunteer.VolunteerExperienceCrudService;
import gr.ihu.ict.resumeinsync.service.crud.language.LanguageCrudService;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import gr.ihu.ict.resumeinsync.service.crud.publication.PublicationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.skill.SkillCrudService;
import gr.ihu.ict.resumeinsync.service.crud.user.UserCrudService;
import gr.ihu.ict.resumeinsync.service.exporter.ExporterService;
import gr.ihu.ict.zotero.publications.importer.service.PublicationItemService;
import io.vavr.Value;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ResumeRpcServiceImpl implements ResumeRpcService {

    private final UserCrudService userCrudService;
    private final ProfileCrudService profileCrudService;
    private final ProfessionalExperienceCrudService professionalExperienceCrudService;
    private final VolunteerExperienceCrudService volunteerExperienceCrudService;
    private final EducationCrudService educationCrudService;
    private final CertificationCrudService certificationCrudService;
    private final SkillCrudService skillCrudService;
    private final LanguageCrudService languageCrudService;
    private final PublicationCrudService publicationCrudService;
    private final ExporterService exporterService;

    private final LinkedInDataImporter linkedInDataImporter;
    private final PublicationItemService publicationItemService;

    @Override
    public Try<Resume> generate(final String userId, SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(userId, "userId is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> userCrudService.findById(userId))
                .flatMap(user -> generate(user, systemLanguage));
    }

    @Override
    public Try<Resume> generate(final User user, SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> profileCrudService.getByUser(user))
                .flatMap(profile -> professionalExperienceCrudService.findByUser(user)
                        .map(Value::toJavaList)
                        .flatMap(professionalExperiences -> volunteerExperienceCrudService.findByUser(user)
                                .map(Value::toJavaList)
                                .flatMap(volunteerExperiences -> educationCrudService.findByUser(user)
                                        .map(Value::toJavaList)
                                        .flatMap(educations -> certificationCrudService.findByUser(user)
                                                .map(Value::toJavaList)
                                                .flatMap(certifications -> skillCrudService.findByUser(user)
                                                        .map(Value::toJavaList)
                                                        .flatMap(skills -> languageCrudService.findByUser(user)
                                                                .map(Value::toJavaList)
                                                                .flatMap(languages -> publicationCrudService
                                                                        .findByUser(user)
                                                                        .map(Value::toJavaList)
                                                                        .flatMap(publications -> Try
                                                                                .of(() -> ResumeLanguageResolver
                                                                                        .resolve(systemLanguage))
                                                                                .flatMap(resumeLanguageMetadata -> Try
                                                                                        .of(ResumeMetadata::builder)
                                                                                        .map(resumeMetadataBuilder -> resumeMetadataBuilder
                                                                                                .generationDate(
                                                                                                        new Date())
                                                                                                .language(
                                                                                                        resumeLanguageMetadata)))
                                                                                .map(ResumeMetadata.ResumeMetadataBuilder::build)
                                                                                .flatMap(
                                                                                        resumeMetadataBuilder -> buildResume(
                                                                                                resumeMetadataBuilder,
                                                                                                user,
                                                                                                profile,
                                                                                                professionalExperiences,
                                                                                                volunteerExperiences,
                                                                                                educations,
                                                                                                certifications,
                                                                                                skills,
                                                                                                languages,
                                                                                                publications))))))))));
    }

    @Override
    public Try<String> generateHtml(final String userId,
            final SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(userId, "userId is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> generate(userId, systemLanguage))
                .flatMap(exporterService::exportHtml);
    }

    @Override
    public Try<String> generateHtml(final User user,
            final SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> generate(user, systemLanguage))
                .flatMap(exporterService::exportHtml);
    }

    @Override
    public Try<Resource> generatePdf(final String userId,
            final SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(userId, "userId is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> generate(userId, systemLanguage))
                .flatMap(exporterService::exportPdf);
    }

    @Override
    public Try<Resource> generatePdf(final User user,
            final SystemLanguage systemLanguage) {
        return Try.run(() -> {
            Objects.requireNonNull(user, "user is null");
            Objects.requireNonNull(systemLanguage, "language is null");
        }).flatMap(ignored -> generate(user, systemLanguage))
                .flatMap(exporterService::exportPdf);
    }

    @Override
    public Try<Void> uploadLinkedIn(final User user, final MultipartFile file) {
        return fileFromMultipartFile(file)
                .map(linkedInDataImporter::parseZip)
                .flatMap(linkedInArchive -> updateProfileLinkedIn(linkedInArchive.getProfile(), user)
                        .flatMap(unused -> createCertificationsLinkedIn(linkedInArchive.getCertifications(), user))
                        .flatMap(unused -> createEducationsLinkedIn(linkedInArchive.getEducations(), user))
                        .flatMap(unused -> createLanguagesLinkedIn(linkedInArchive.getLanguages(), user))
                        .flatMap(unused -> createPositionsLinkedIn(linkedInArchive.getPositions(), user))
                        .flatMap(unused -> createPublicationsLinkedIn(linkedInArchive.getPublications(), user))
                        .flatMap(unused -> createSkillsLinkedIn(linkedInArchive.getSkills(), user))
                        .map(unused -> null));
    }

    private Try<io.vavr.collection.List<Skill>> createSkillsLinkedIn(
            List<gr.ihu.ict.linkedin.data.importer.model.csv.Skill> skills, User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(skills)
                .map(skill -> {
                    Skill skillToCreate = new Skill();

                    skillToCreate.setUser(user);

                    skillToCreate.setName(skill.getName());
                    skillToCreate.setImportSource("LinkedIn");

                    return skillToCreate;
                }))
                .flatMap(skillCrudService::createAll);
    }

    private Try<io.vavr.collection.List<Publication>> createPublicationsLinkedIn(
            List<gr.ihu.ict.linkedin.data.importer.model.csv.Publication> publications, User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(publications)
                .map(publication -> {
                    Publication publicationToCreate = new Publication();

                    publicationToCreate.setUser(user);

                    publicationToCreate.setTitle(publication.getName());
                    publicationToCreate.setDescription(publication.getDescription());
                    publicationToCreate.setPublisher(publication.getPublisher());
                    final Date publicationDate = dateFromLinkedInPublication(publication.getPublishedOn()).get();
                    publicationToCreate.setPublicationDate(publicationDate);
                    publicationToCreate.setUrl(publication.getUrl());
                    // ToDo: Parse co-authors
                    publicationToCreate.setAuthors("");
                    publicationToCreate.setImportSource("LinkedIn");

                    return publicationToCreate;
                }))
                .flatMap(publicationCrudService::createAll);

    }

    private Try<Date> dateFromLinkedInPublication(String publishedOn) {
        return Try.of(() -> DateUtils.parseDate(publishedOn, "MMM dd, YYYY"));
    }

    private Try<io.vavr.collection.List<ProfessionalExperience>> createPositionsLinkedIn(List<Position> positions,
            User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(positions)
                .map(position -> {
                    final ProfessionalExperience professionalExperienceToCreate = new ProfessionalExperience();

                    professionalExperienceToCreate.setUser(user);

                    professionalExperienceToCreate.setTitle(position.getTitle());
                    professionalExperienceToCreate.setCompany(position.getCompanyName());
                    professionalExperienceToCreate.setDescription(position.getDescription());
                    professionalExperienceToCreate.setLocation(position.getLocation());
                    final DateRange dateRange = dateRangeFromLinkedInCertificate(
                            position.getStartedOn(),
                            position.getFinishedOn());
                    professionalExperienceToCreate.setDateRange(dateRange);
                    Boolean isCurrently = Objects.isNull(dateRange.getEndDate());
                    professionalExperienceToCreate.setCurrently(isCurrently);
                    professionalExperienceToCreate.setImportSource("LinkedIn");

                    return professionalExperienceToCreate;
                }))
                .flatMap(professionalExperienceCrudService::createAll);

    }

    private Try<io.vavr.collection.List<Language>> createLanguagesLinkedIn(
            List<gr.ihu.ict.linkedin.data.importer.model.csv.Language> languages, User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(languages)
                .map(language -> {
                    Language languageToCreate = new Language();

                    languageToCreate.setUser(user);
                    languageToCreate.setName(language.getName());
                    languageToCreate.setProficiency(LanguageProficiency.parse(language.getProficiency()));
                    languageToCreate.setImportSource("LinkedIn");

                    return languageToCreate;
                }))
                .flatMap(languageCrudService::createAll);
    }

    private Try<io.vavr.collection.List<Education>> createEducationsLinkedIn(
            final List<gr.ihu.ict.linkedin.data.importer.model.csv.Education> educations,
            final User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(educations)
                .map(education -> {
                    final Education educationToCreate = new Education();

                    educationToCreate.setUser(user);

                    educationToCreate.setSchool(education.getSchoolName());
                    educationToCreate.setDegree(education.getDegreeName());
                    educationToCreate.setField(education.getDegreeName());
                    final DateRange dateRange = dateRangeFromLinkedInEducation(
                            education.getStartDate(),
                            education.getEndDate());
                    educationToCreate.setGrade(education.getDegreeName());
                    educationToCreate.setDescription(education.getNotes());
                    educationToCreate.setDateRange(dateRange);
                    educationToCreate.setImportSource("LinkedIn");

                    return educationToCreate;
                }))
                .flatMap(educationCrudService::createAll);
    }

    private Try<io.vavr.collection.List<Certification>> createCertificationsLinkedIn(
            final List<gr.ihu.ict.linkedin.data.importer.model.csv.Certification> certifications,
            final User user) {
        return Try.of(() -> io.vavr.collection.List.ofAll(certifications)
                .map(certification -> {
                    final Certification certificationToCreate = new Certification();

                    certificationToCreate.setUser(user);

                    certificationToCreate.setName(certification.getName());
                    certificationToCreate.setOrganization(certification.getAuthority());
                    final DateRange dateRange = dateRangeFromLinkedInCertificate(
                            certification.getStartedOn(),
                            certification.getFinishedOn());
                    final Boolean canExpire = BooleanUtils.NOT(Objects.isNull(dateRange.getEndDate()));
                    certificationToCreate.setCanExpire(canExpire);
                    certificationToCreate.setDateRange(dateRange);
                    certificationToCreate.setOriginalId(certification.getLicenseNumber());
                    certificationToCreate.setOriginalUrl(certification.getUrl());
                    certificationToCreate.setImportSource("LinkedIn");

                    return certificationToCreate;
                }))
                .flatMap(certificationCrudService::createAll);
    }

    private DateRange dateRangeFromLinkedInCertificate(String startedOn, String finishedOn) {
        Try<Date> startedOnDate = Try.of(() -> DateUtils.parseDate(startedOn, "MMM YYYY"));
        Try<Date> finishedOnDate = Try.of(() -> DateUtils.parseDate(finishedOn, "MMM YYYY"));

        if (finishedOnDate.isEmpty()) {
            if (startedOnDate.isEmpty()) {
                return new DateRange() {
                    {
                        setStartDate(new Date());
                    }
                };
            }
            return new DateRange() {
                {
                    setStartDate(startedOnDate.get());
                }
            };
        }

        return new DateRange() {
            {
                setStartDate(startedOnDate.get());
                setEndDate(finishedOnDate.get());
            }
        };
    }

    private DateRange dateRangeFromLinkedInEducation(String startedOn, String finishedOn) {
        Try<Date> startedOnDate = Try.of(() -> DateUtils.parseDate(startedOn, "YYYY"));
        Try<Date> finishedOnDate = Try.of(() -> DateUtils.parseDate(finishedOn, "YYYY"));

        if (finishedOnDate.isEmpty()) {
            if (startedOnDate.isEmpty()) {
                return new DateRange() {
                    {
                        setStartDate(new Date());
                    }
                };
            }
            return new DateRange() {
                {
                    setStartDate(startedOnDate.get());
                }
            };
        }

        return new DateRange() {
            {
                setStartDate(startedOnDate.get());
                setEndDate(finishedOnDate.get());
            }
        };
    }

    private Try<Profile> updateProfileLinkedIn(
            final gr.ihu.ict.linkedin.data.importer.model.csv.Profile profileOfLinkedIn, User user) {
        return profileCrudService.getByUser(user)
                .map(profile -> {
                    profile.setFirstName(profileOfLinkedIn.getFirstName());
                    profile.setLastName(profileOfLinkedIn.getLastName());
                    profile.setBio(profileOfLinkedIn.getHeadline());
                    return profile;
                })
                .flatMap(profile -> profileCrudService.updateById(profile, profile.getId()));
    }

    @Override
    public Try<Void> uploadEuropass(final User user, final MultipartFile file) {
        return null;
    }

    private Try<File> fileFromMultipartFile(MultipartFile file) {
        return Try.of(() -> File.createTempFile("ris", "temp"))
        .flatMap(tempFile -> Try.of(() -> {
            if(tempFile == null) {
                return null;
            }
            if(file == null) {
                return null;
            }
            file.transferTo(tempFile);
            return tempFile;
        }));
    }

    private Try<Resume> buildResume(final ResumeMetadata resumeMetadata,
            final User user,
            final Profile profile,
            final List<ProfessionalExperience> professionalExperiences,
            final List<VolunteerExperience> volunteerExperiences,
            final List<Education> educations,
            final List<Certification> certifications,
            final List<Skill> skills,
            final List<Language> languages,
            final List<Publication> publications) {
        return Try.of(Resume::builder)
                .map(resume -> resume
                        .metadata(resumeMetadata)
                        .user(user)
                        .profile(profile)
                        .professionalExperiences(professionalExperiences)
                        .volunteerExperiences(volunteerExperiences)
                        .educations(educations)
                        .certifications(certifications)
                        .skills(skills)
                        .languages(languages)
                        .publications(publications))
                .map(Resume.ResumeBuilder::build);
    }

    @Override
    public Try<Void> reset(User user) {
        final io.vavr.collection.List<UserOwnedCrudService<? extends AbstractUserOwnedEntity>> userOwnedCrudServices = io.vavr.collection.List
                .of(professionalExperienceCrudService,
                        volunteerExperienceCrudService,
                        educationCrudService,
                        certificationCrudService,
                        skillCrudService,
                        languageCrudService,
                        publicationCrudService);

        return Try.sequence(
                userOwnedCrudServices
                        .map(userOwnedCrudService -> {
                            return userOwnedCrudService.findByUser(user)
                                    .map(userOwnedEntities -> userOwnedEntities.map(AbstractUserOwnedEntity::getId))
                                    .map(userOwnedCrudService::deleteAll)
                                    .get();
                        }))
                .map(Seq::toList)
                .map(ignored -> null);
    }

    @Override
    public Try<Void> importZotero(User user, String userId) {
        return Try.of(() -> publicationItemService.findAllPublicationItemsByUserId(userId))
                .map(io.vavr.collection.List::ofAll)
                .flatMap(publicationItems -> Try.sequence(
                                publicationItems
                                        .map(publication -> Try.of(() -> {
                                            String title = publication.getTitle();
                                            String abstractNote = publication.getAbstractNote();
                                            Date date = publication.getDate();
                                            List<String> creators = publication.getCreators();
                                            String authors = io.vavr.collection.List.ofAll(creators)
                                                    .reduce((c, n) -> c.concat(",").concat(n));

                                            String url = publication.getUrl();

                                            final Publication publicationToCreate = new Publication();
                                            publicationToCreate.setTitle(title);
                                            publicationToCreate.setDescription(abstractNote);
                                            publicationToCreate.setAuthors(authors);
                                            publicationToCreate.setUrl(url);
                                            publicationToCreate.setPublicationDate(date);
                                            publicationToCreate.setUser(user);
                                            publicationToCreate.setPublisher("");
                                            publicationToCreate.setImportSource("Zotero");

                                            return publicationToCreate;
                                        })))
                        .map(Seq::toList)
                        .flatMap(publicationCrudService::createAll)
                        .flatMap(_ignored -> Try.run(() -> {})));
    }
}

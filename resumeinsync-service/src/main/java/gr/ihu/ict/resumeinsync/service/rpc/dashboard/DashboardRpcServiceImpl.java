package gr.ihu.ict.resumeinsync.service.rpc.dashboard;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.model.Dashboard;
import gr.ihu.ict.resumeinsync.domain.model.RecordCounts;
import gr.ihu.ict.resumeinsync.service.crud.certification.CertificationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.education.EducationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.professional.ProfessionalExperienceCrudService;
import gr.ihu.ict.resumeinsync.service.crud.experience.volunteer.VolunteerExperienceCrudService;
import gr.ihu.ict.resumeinsync.service.crud.language.LanguageCrudService;
import gr.ihu.ict.resumeinsync.service.crud.profile.ProfileCrudService;
import gr.ihu.ict.resumeinsync.service.crud.publication.PublicationCrudService;
import gr.ihu.ict.resumeinsync.service.crud.skill.SkillCrudService;
import gr.ihu.ict.resumeinsync.service.crud.user.UserCrudService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DashboardRpcServiceImpl implements DashboardRpcService {

        private final UserCrudService userCrudService;
        private final ProfileCrudService profileCrudService;
        private final ProfessionalExperienceCrudService professionalExperienceCrudService;
        private final VolunteerExperienceCrudService volunteerExperienceCrudService;
        private final EducationCrudService educationCrudService;
        private final CertificationCrudService certificationCrudService;
        private final SkillCrudService skillCrudService;
        private final LanguageCrudService languageCrudService;
        private final PublicationCrudService publicationCrudService;

        public DashboardRpcServiceImpl(final UserCrudService userCrudService,
                        final ProfileCrudService profileCrudService,
                        final ProfessionalExperienceCrudService professionalExperienceCrudService,
                        final VolunteerExperienceCrudService volunteerExperienceCrudService,
                        final EducationCrudService educationCrudService,
                        final CertificationCrudService certificationCrudService,
                        final SkillCrudService skillCrudService,
                        final LanguageCrudService languageCrudService,
                        final PublicationCrudService publicationCrudService) {
                this.userCrudService = userCrudService;
                this.profileCrudService = profileCrudService;
                this.professionalExperienceCrudService = professionalExperienceCrudService;
                this.volunteerExperienceCrudService = volunteerExperienceCrudService;
                this.educationCrudService = educationCrudService;
                this.certificationCrudService = certificationCrudService;
                this.skillCrudService = skillCrudService;
                this.languageCrudService = languageCrudService;
                this.publicationCrudService = publicationCrudService;
        }

        @Override
        public Try<Dashboard> get(final String userId) {
                return Try.run(() -> Objects.requireNonNull(userId, "userId is null"))
                                .flatMap(ignored -> userCrudService.findById(userId))
                                .flatMap(this::get);
        }

        @Override
        public Try<Dashboard> get(final User user) {
                return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                                .flatMap(ignored -> profileCrudService.getByUser(user))
                                .flatMap(profile -> Option.of(profile.getUser()).toTry()
                                                .flatMap(userFromProfile -> calculateRecords(userFromProfile)
                                                                .flatMap(recordCounts -> Try.of(Dashboard::builder)
                                                                                .map(dashboardBuilder -> dashboardBuilder
                                                                                                .profile(profile)
                                                                                                .recordCounts(recordCounts)))))
                                .map(Dashboard.DashboardBuilder::build);
        }

        private Try<RecordCounts> calculateRecords(final User user) {
                return Try.run(() -> Objects.requireNonNull(user, "user is null"))
                                .flatMap(ignored -> professionalExperienceCrudService.getCountByUser(user))
                                .flatMap(professionalsCount -> volunteerExperienceCrudService.getCountByUser(user)
                                                .flatMap(volunteersCount -> educationCrudService.getCountByUser(user)
                                                                .flatMap(educationsCount -> certificationCrudService
                                                                                .getCountByUser(user)
                                                                                .flatMap(certificationsCounter -> skillCrudService
                                                                                                .getCountByUser(user)
                                                                                                .flatMap(skillsCount -> languageCrudService
                                                                                                                .getCountByUser(user)
                                                                                                                .flatMap(languagesCount -> publicationCrudService
                                                                                                                                .getCountByUser(user)
                                                                                                                                .flatMap(publicationsCount -> buildRecordCount(
                                                                                                                                                professionalsCount,
                                                                                                                                                volunteersCount,
                                                                                                                                                educationsCount,
                                                                                                                                                certificationsCounter,
                                                                                                                                                skillsCount,
                                                                                                                                                languagesCount,
                                                                                                                                                publicationsCount))))))));
        }

        private Try<RecordCounts> buildRecordCount(final Integer professionalsCount,
                        final Integer volunteersCount,
                        final Integer educationsCount,
                        final Integer certificationsCounter,
                        final Integer skillsCount,
                        final Integer languagesCount,
                        final Integer publicationsCount) {
                return Try.of(RecordCounts::builder)
                                .map(recordCountsBuilder -> recordCountsBuilder
                                                .professionalExperiences(professionalsCount)
                                                .volunteerExperiences(volunteersCount)
                                                .educations(educationsCount)
                                                .certifications(certificationsCounter)
                                                .skills(skillsCount)
                                                .languages(languagesCount)
                                                .publications(publicationsCount))
                                .map(RecordCounts.RecordCountsBuilder::build);
        }
}

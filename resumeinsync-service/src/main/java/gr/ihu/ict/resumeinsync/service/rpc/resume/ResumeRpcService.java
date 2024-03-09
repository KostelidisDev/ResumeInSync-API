package gr.ihu.ict.resumeinsync.service.rpc.resume;

import gr.ihu.ict.resumeinsync.common.constants.SystemLanguage;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.domain.model.resume.Resume;
import io.vavr.control.Try;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeRpcService {

        @PreAuthorize("hasPermission(#userId, " +
                        "T(gr.ihu.ict.resumeinsync.domain.constant.Entities).USER, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Resume> generate(String userId, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Resume> generate(User user, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#userId, " +
                        "T(gr.ihu.ict.resumeinsync.domain.constant.Entities).USER, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<String> generateHtml(String userId, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<String> generateHtml(User user, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#userId, " +
                        "T(gr.ihu.ict.resumeinsync.domain.constant.Entities).USER, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Resource> generatePdf(String userId, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).READ)")
        Try<Resource> generatePdf(User user, SystemLanguage systemLanguage);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Void> uploadLinkedIn(User user, MultipartFile file);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Void> uploadEuropass(User user, MultipartFile file);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Void> importZotero(User user, String userId);

        @PreAuthorize("hasPermission(#user, " +
                        "T(gr.ihu.ict.resumeinsync.common.constants.security.Permission).WRITE)")
        Try<Void> reset(User user);

}

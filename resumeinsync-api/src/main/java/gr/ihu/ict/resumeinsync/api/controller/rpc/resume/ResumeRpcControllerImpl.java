package gr.ihu.ict.resumeinsync.api.controller.rpc.resume;

import gr.ihu.ict.resumeinsync.common.constants.SystemLanguage;
import gr.ihu.ict.resumeinsync.dto.request.ImportZoteroDto;
import gr.ihu.ict.resumeinsync.dto.response.ResumeDTO;
import gr.ihu.ict.resumeinsync.mapper.other.ResumeMapper;
import gr.ihu.ict.resumeinsync.security.util.AuthUtils;
import gr.ihu.ict.resumeinsync.service.rpc.resume.ResumeRpcService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/rpc/resume")
@Slf4j
public class ResumeRpcControllerImpl implements ResumeRpcController {

        private final ResumeRpcService resumeRpcService;
        private final ResumeMapper resumeMapper;

        public ResumeRpcControllerImpl(final ResumeRpcService resumeRpcService,
                        final ResumeMapper resumeMapper) {
                this.resumeRpcService = resumeRpcService;
                this.resumeMapper = resumeMapper;
        }

        @Override
        @GetMapping({ "/json", "/json/" })
        public ResponseEntity<ResumeDTO> generateJson(final HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> AuthUtils.getAuthenticatedUser()
                                                .flatMap(user -> resumeRpcService.generate(user, language)))
                                .map(resumeMapper::toDTO)
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping({ "/json/{userId}" })
        public ResponseEntity<ResumeDTO> generateJson(@PathVariable final String userId,
                        final HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> resumeRpcService.generate(userId, language))
                                .map(resumeMapper::toDTO)
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping({ "/html", "/html/" })
        public ResponseEntity<String> generateHtml(final HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> AuthUtils.getAuthenticatedUser()
                                                .flatMap(user -> resumeRpcService.generateHtml(user, language)))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping({ "/html/{userId}" })
        public ResponseEntity<String> generateHtml(@PathVariable final String userId,
                        final HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> resumeRpcService.generateHtml(userId, language))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping(value = { "/pdf", "/pdf/" }, produces = MediaType.APPLICATION_PDF_VALUE)
        public ResponseEntity<Resource> generatePdf(HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> AuthUtils.getAuthenticatedUser()
                                                .flatMap(user -> resumeRpcService.generatePdf(user, language)))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @GetMapping(value = "/pdf/{userId}", produces = MediaType.APPLICATION_PDF_VALUE)
        public ResponseEntity<Resource> generatePdf(@PathVariable final String userId,
                        final HttpServletRequest httpServletRequest) {
                return resolveLanguage(httpServletRequest)
                                .flatMap(language -> resumeRpcService.generatePdf(userId, language))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @PostMapping("/upload/linkedin")
        public ResponseEntity<Void> uploadLinkedIn(@RequestPart final MultipartFile file) {
                return AuthUtils.getAuthenticatedUser()
                                .flatMap(user -> resumeRpcService.uploadLinkedIn(user, file))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @PostMapping("/upload/europass")
        public ResponseEntity<Void> uploadEuropass(@RequestPart final MultipartFile file) {
                return AuthUtils.getAuthenticatedUser()
                                .flatMap(user -> resumeRpcService.uploadEuropass(user, file))
                                .map(ResponseEntity::ok)
                                .get();
        }

        private Try<SystemLanguage> resolveLanguage(final HttpServletRequest httpServletRequest) {
                return Try.run(() -> Objects.requireNonNull(httpServletRequest, "httpServletRequest is null"))
                                .flatMap(ignored -> Option.of(httpServletRequest.getParameter("language")).toTry())
                                .flatMap(language -> Option.ofOptional(SystemLanguage.resolve(language)).toTry())
                                .recover(throwable -> {
                                        log.warn("Can't resolve the language, fallback to default", throwable);
                                        return SystemLanguage.ENGLISH;
                                });
        }

        @Override
        @PostMapping("/reset")
        public ResponseEntity<Void> reset() {
                return AuthUtils.getAuthenticatedUser()
                                .flatMap(user -> resumeRpcService.reset(user))
                                .map(ResponseEntity::ok)
                                .get();
        }

        @Override
        @PostMapping("/import/zotero")
        public ResponseEntity<Void> importZotero(@RequestBody ImportZoteroDto importZoteroDto) {
               return AuthUtils.getAuthenticatedUser()
               .flatMap(user -> Try.of(() -> importZoteroDto.getUserId())
               .flatMap(zoteroUserId -> resumeRpcService.importZotero(user, zoteroUserId)))
               .map(ResponseEntity::ok)
               .get();
        }
}

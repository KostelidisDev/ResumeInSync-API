package gr.ihu.ict.resumeinsync.api.controller.rpc.resume;

import gr.ihu.ict.resumeinsync.dto.request.ImportZoteroDto;
import gr.ihu.ict.resumeinsync.dto.response.ResumeDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rpc/resume")
public interface ResumeRpcController {

    @GetMapping({ "/json", "/json/" })
    ResponseEntity<ResumeDTO> generateJson(HttpServletRequest httpServletRequest);

    @GetMapping("/json/{userId}")
    ResponseEntity<ResumeDTO> generateJson(@PathVariable String userId,
            HttpServletRequest httpServletRequest);

    @GetMapping({ "/html", "/html/" })
    ResponseEntity<String> generateHtml(HttpServletRequest httpServletRequest);

    @GetMapping("/html/{userId}")
    ResponseEntity<String> generateHtml(@PathVariable String userId,
            HttpServletRequest httpServletRequest);

    @GetMapping({ "/pdf", "/pdf/" })
    ResponseEntity<Resource> generatePdf(HttpServletRequest httpServletRequest);

    @GetMapping("/pdf/{userId}")
    ResponseEntity<Resource> generatePdf(@PathVariable String userId,
            HttpServletRequest httpServletRequest);

    @PostMapping("/upload/linkedin")
    ResponseEntity<Void> uploadLinkedIn(@RequestPart final MultipartFile file);

    @PostMapping("/upload/europass")
    ResponseEntity<Void> uploadEuropass(@RequestPart final MultipartFile file);

    @PostMapping("/import/zotero")
    ResponseEntity<Void> importZotero(@RequestBody ImportZoteroDto importZoteroDto);

    @PostMapping("/reset")
    ResponseEntity<Void> reset();
}

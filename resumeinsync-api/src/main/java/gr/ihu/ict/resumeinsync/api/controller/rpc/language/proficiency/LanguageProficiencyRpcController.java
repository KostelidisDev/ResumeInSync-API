package gr.ihu.ict.resumeinsync.api.controller.rpc.language.proficiency;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rpc/language/proficiency")
public interface LanguageProficiencyRpcController {

    @GetMapping("/")
    ResponseEntity<List<String>> getLanguageProficiencies();
}

package gr.ihu.ict.resumeinsync.api.controller.rpc.language.proficiency;

import gr.ihu.ict.resumeinsync.common.constants.LanguageProficiency;
import io.vavr.Value;
import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rpc/language/proficiency")
public class LanguageProficiencyRpcControllerImpl implements LanguageProficiencyRpcController {

    @Override
    @GetMapping("/")
    public ResponseEntity<List<String>> getLanguageProficiencies() {
        return Try.of(LanguageProficiency::values)
                .map(io.vavr.collection.List::of)
                .flatMap(languageProficiencies -> Try.sequence(
                        languageProficiencies.map(languageProficiency -> Try.of(languageProficiency::name))))
                .map(Value::toJavaList)
                .map(ResponseEntity::ok)
                .get();
    }
}

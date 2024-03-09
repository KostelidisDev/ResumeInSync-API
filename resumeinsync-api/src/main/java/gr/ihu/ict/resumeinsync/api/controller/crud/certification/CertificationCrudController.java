package gr.ihu.ict.resumeinsync.api.controller.crud.certification;

import gr.ihu.ict.resumeinsync.api.controller.crud.UserOwnedCrudController;
import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import gr.ihu.ict.resumeinsync.dto.system.input.CertificationInputDTO;
import gr.ihu.ict.resumeinsync.dto.system.output.CertificationOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certifications")
public interface CertificationCrudController
                extends UserOwnedCrudController<CertificationInputDTO, CertificationOutputDTO, Certification> {
}

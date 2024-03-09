package gr.ihu.ict.resumeinsync.api.controller.crud;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import gr.ihu.ict.resumeinsync.domain.repository.Repository;
import gr.ihu.ict.resumeinsync.dto.AbstractEntityDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.service.crud.CrudService;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public interface CrudController<INPUT_TYPE extends AbstractEntityDTO, OUTPUT_TYPE extends AbstractEntityDTO, ENTITY_TYPE extends AbstractEntity, REPOSITORY_TYPE extends Repository<ENTITY_TYPE>> {

    CrudService<ENTITY_TYPE, REPOSITORY_TYPE> getCrudService();

    Mapper<ENTITY_TYPE, INPUT_TYPE> getInputMapperDTO();

    Mapper<ENTITY_TYPE, OUTPUT_TYPE> getOutputMapperDTO();

    Logger getLogger();

    @PostMapping({ "/", "" })
    default ResponseEntity<OUTPUT_TYPE> create(final @RequestBody @Valid INPUT_TYPE requestBody) {
        return Try.of(() -> getInputMapperDTO().fromDTO(requestBody))
                .flatMap(entity_type -> getCrudService().create(entity_type))
                .flatMap(entity_type -> Try.of(() -> getOutputMapperDTO().toDTO(entity_type)))
                .map(ResponseEntity::ok)
                .get();
    }

    @GetMapping("/{id}")
    default ResponseEntity<OUTPUT_TYPE> findById(final @PathVariable String id) {
        return getCrudService().findById(id)
                .flatMap(entity_types -> Try.of(() -> getOutputMapperDTO().toDTO(entity_types)))
                .map(ResponseEntity::ok)
                .get();
    }

    @GetMapping({ "/", "" })
    default ResponseEntity<List<OUTPUT_TYPE>> findByParameters(final HttpServletRequest httpServletRequest) {
        return Option.of(httpServletRequest.getParameterMap()).toTry()
                .recover(throwable -> new HashMap<>())
                .map(io.vavr.collection.HashMap::ofAll)
                .flatMap(stringMap -> getCrudService().findByParameters(stringMap))
                .map(Value::toJavaList)
                .flatMap(entity_types -> Try.of(() -> getOutputMapperDTO().toDTO(entity_types)))
                .map(ResponseEntity::ok)
                .get();
    }

    @PutMapping("/{id}")
    default ResponseEntity<OUTPUT_TYPE> updateById(final @PathVariable String id,
            final @RequestBody @Valid INPUT_TYPE requestBody) {
        return Try.of(() -> getInputMapperDTO().fromDTO(requestBody))
                .flatMap(entity_type -> getCrudService().updateById(entity_type, id))
                .flatMap(entity_type -> Try.of(() -> getOutputMapperDTO().toDTO(entity_type)))
                .map(ResponseEntity::ok)
                .get();
    }

    @DeleteMapping("/{id}")
    default ResponseEntity<Void> deleteById(@PathVariable final String id) {
        return getCrudService().deleteById(id)
                .map(ignored -> ResponseEntity.noContent())
                .map(ResponseEntity.HeadersBuilder::<Void>build)
                .get();
    }
}

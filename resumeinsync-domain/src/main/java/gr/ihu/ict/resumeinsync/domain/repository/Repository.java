package gr.ihu.ict.resumeinsync.domain.repository;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface Repository<TYPE extends AbstractEntity> extends JpaRepository<TYPE, String> {

    @Query("SELECT e FROM #{#entityName} AS e WHERE e.id = :id")
    Optional<TYPE> findOne(@Param("id") String id);

    @Query("SELECT e.id FROM #{#entityName} AS e")
    List<String> getAllIds();

    default Boolean hasRecords() {
        return count() >= 1;
    }
}

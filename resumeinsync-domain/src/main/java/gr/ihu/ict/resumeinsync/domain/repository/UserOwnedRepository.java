package gr.ihu.ict.resumeinsync.domain.repository;

import gr.ihu.ict.resumeinsync.domain.entity.AbstractUserOwnedEntity;
import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UserOwnedRepository<TYPE extends AbstractUserOwnedEntity> extends Repository<TYPE> {

    @Query("SELECT e FROM #{#entityName} AS e WHERE e.user = :user")
    List<TYPE> getAllByUser(final User user);

    @Query("SELECT count(e.id) FROM #{#entityName} AS e WHERE e.user = :user")
    Integer getCountByUser(final User user);
}

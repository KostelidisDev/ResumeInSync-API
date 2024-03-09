package gr.ihu.ict.resumeinsync.service.crud.skill;

import gr.ihu.ict.resumeinsync.domain.entity.system.Skill;
import gr.ihu.ict.resumeinsync.domain.repository.UserOwnedRepository;
import gr.ihu.ict.resumeinsync.domain.repository.system.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SkillCrudServiceImpl implements SkillCrudService {

    private final SkillRepository skillRepository;

    public SkillCrudServiceImpl(final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public UserOwnedRepository<Skill> getRepository() {
        return skillRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Skill> entityClass() {
        return Skill.class;
    }
}

package gr.ihu.ict.resumeinsync.mapper.system.output;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.dto.system.output.UserOutputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class,
                RoleOutputMapper.class
})
public interface UserOutputMapper extends Mapper<User, UserOutputDTO> {

        @Override
        UserOutputDTO toDTO(User object);

        @Override
        List<UserOutputDTO> toDTO(List<User> objects);

        @Override
        @Mappings({
                        @Mapping(target = "password", ignore = true),
        })
        User fromDTO(UserOutputDTO DTO);

        @Override
        List<User> fromDTO(List<UserOutputDTO> DTOs);
}

package gr.ihu.ict.resumeinsync.mapper.system.input;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.dto.system.input.UserInputDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import gr.ihu.ict.resumeinsync.mapper.ReferenceMapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@org.mapstruct.Mapper(uses = {
                ReferenceMapper.class,
                RoleInputMapper.class
})
public interface UserInputMapper extends Mapper<User, UserInputDTO> {

        @Override
        UserInputDTO toDTO(User object);

        @Override
        List<UserInputDTO> toDTO(List<User> objects);

        @Override
        @Mappings({
                        @Mapping(target = "enabled", ignore = true),
                        @Mapping(target = "accountNonLocked", ignore = true),
                        @Mapping(target = "accountNonExpired", ignore = true),
                        @Mapping(target = "credentialsNonExpired", ignore = true),
                        @Mapping(target = "roles", ignore = true)
        })
        User fromDTO(UserInputDTO DTO);

        @Override
        List<User> fromDTO(List<UserInputDTO> DTOs);
}

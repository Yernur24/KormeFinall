package kz.bit.kormefinall.mapper;

import kz.bit.kormefinall.dto.UserDTO;
import kz.bit.kormefinall.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "fullName", target = "title")
    UserDTO toUserDTO(User user);

    @Mapping(source = "title", target = "fullName")
    User toEntityUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);

    List<User> toEntityUserList(List<UserDTO> userDTOList);
}
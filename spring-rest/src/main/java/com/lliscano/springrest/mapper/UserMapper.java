package com.lliscano.springrest.mapper;



import com.lliscano.springrest.dto.UserDTO;
import com.lliscano.springrest.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(Users user);
    Users toEntity(UserDTO userDTO);
}

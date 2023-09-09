package com.lliscano.springrest.service;

import com.lliscano.springrest.dto.UserDTO;
import com.lliscano.springrest.entity.Users;
import com.lliscano.springrest.mapper.UserMapper;
import com.lliscano.springrest.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final UserMapper mapper;

    public UserDTO saveUser(UserDTO userDTO) {
        Users user = this.usersRepository.save(this.mapper.toEntity(userDTO));
        return this.mapper.toDto(user);
    }

}

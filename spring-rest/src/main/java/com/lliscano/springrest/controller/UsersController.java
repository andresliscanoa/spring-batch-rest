package com.lliscano.springrest.controller;

import com.lliscano.springrest.dto.UserDTO;
import com.lliscano.springrest.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService service;
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @RequestBody UserDTO userDTO
    ) {
        return new ResponseEntity<>(this.service.saveUser(userDTO), HttpStatus.CREATED);
    }
}

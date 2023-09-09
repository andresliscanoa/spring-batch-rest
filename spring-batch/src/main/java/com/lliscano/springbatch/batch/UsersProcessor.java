package com.lliscano.springbatch.batch;

import com.lliscano.springbatch.dto.UserDTO;
import com.lliscano.springbatch.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class UsersProcessor implements ItemProcessor<Users, UserDTO> {
    @Override
    public UserDTO process(Users item) throws Exception {
        log.debug("ITEM: {}",item);
        final UserDTO userDTO = new UserDTO();
        userDTO.setFullName(item.getFirstname().toUpperCase()+" "+item.getLastname().toUpperCase());
        userDTO.setGender(item.getGender());
        log.debug("DTO: {}",userDTO);
        return userDTO;
    }
}

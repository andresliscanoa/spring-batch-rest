package com.lliscano.springbatch.batch;

import com.lliscano.springbatch.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestItemWriter implements ItemWriter<UserDTO> {

    @Value("${service.endpoint.url}")
    private String url;

    @Override
    public void write(Chunk<? extends UserDTO> chunk) throws Exception {
        chunk.forEach(userDTO -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserDTO> response = restTemplate.postForEntity(url,userDTO, UserDTO.class);
            log.debug("Response: {}", response);
        });
    }
}

package com.nihalramtripathi.authservice.client;

import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("/internal/users/register")
    void registerUser(@RequestBody RegisterRequestDTO request);
}

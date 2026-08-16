package com.nihalramtripathi.authservice.client;

import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import com.nihalramtripathi.commoncore.dto.response.UserLookupResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("/internal/users/register")
    UserDetailsResponseDTO registerUser(@RequestBody RegisterRequestDTO request);

    @PostMapping("/internal/users/checkUserLogin")
    UserLookupResponseDTO checkUserLogin(@RequestBody CommonRequestDTO request);
}

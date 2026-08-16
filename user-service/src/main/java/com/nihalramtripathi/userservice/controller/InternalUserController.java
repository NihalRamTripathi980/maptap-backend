package com.nihalramtripathi.userservice.controller;

import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserLookupResponseDTO;
import com.nihalramtripathi.userservice.dto.UserRegistrationRequestDTO;
import com.nihalramtripathi.userservice.dto.UserResponseDTO;
import com.nihalramtripathi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @PostMapping("/checkUserLogin")
    UserLookupResponseDTO checkUserLogin(@RequestBody CommonRequestDTO request) {
        return userService.checkUserLogin(request);
    }

    @PostMapping("/register")
    public UserResponseDTO userRegistration(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        return userService.register(userRegistrationRequestDTO);
    }


}

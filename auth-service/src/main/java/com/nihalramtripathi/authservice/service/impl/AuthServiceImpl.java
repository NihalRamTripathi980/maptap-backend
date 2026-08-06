package com.nihalramtripathi.authservice.service.impl;

import com.nihalramtripathi.authservice.client.UserClient;
import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {


    private final UserClient userClient;



    @Override
    public void register(RegisterRequestDTO request) {
       userClient.registerUser(request);
    }

    @Override
    public AuthResponseDTO setPasscode(SetPasscodeRequestDTO request) {
        return null;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        return null;
    }
}

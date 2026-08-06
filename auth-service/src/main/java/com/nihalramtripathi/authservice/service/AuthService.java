package com.nihalramtripathi.authservice.service;

import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import org.springframework.stereotype.Service;


public interface AuthService {

    void register(RegisterRequestDTO request);

    AuthResponseDTO setPasscode(SetPasscodeRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);

}

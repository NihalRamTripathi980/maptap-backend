package com.nihalramtripathi.authservice.service;

import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.dto.response.MobileCheckResponseDTO;
import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;


public interface AuthService {

    MobileCheckResponseDTO checkUserLogin(CommonRequestDTO request);

    UserDetailsResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO setPasscode(SetPasscodeRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);

}

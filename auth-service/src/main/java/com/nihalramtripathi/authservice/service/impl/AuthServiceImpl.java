package com.nihalramtripathi.authservice.service.impl;

import com.nihalramtripathi.authservice.client.UserClient;
import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.dto.response.MobileCheckResponseDTO;
import com.nihalramtripathi.authservice.service.AuthService;
import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import com.nihalramtripathi.commoncore.dto.response.UserLookupResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {


    private final UserClient userClient;


    @Override
    public MobileCheckResponseDTO checkUserLogin(CommonRequestDTO request) {
        UserLookupResponseDTO lookupResponseDTO = userClient.checkUserLogin(request);
         if(lookupResponseDTO.isExists()){

             return  MobileCheckResponseDTO.builder()
                     .userExists(true)
                     .nextStep("LOGIN")
                     .message("User already registered")
                     .userDetails(UserDetailsResponseDTO
                             .builder()
                             .userId(lookupResponseDTO.getUserId())
                             .userName(lookupResponseDTO.getUserName())
                             .build())
                     .build();
         }else{
             return MobileCheckResponseDTO.builder()
                     .userExists(false)
                     .nextStep("VERIFY_OTP")
                     .message("OTP sent successfully")
                     .build();
         }

    }

    @Override
    public UserDetailsResponseDTO register(RegisterRequestDTO request) {
      return userClient.registerUser(request);
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

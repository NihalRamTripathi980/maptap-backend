package com.nihalramtripathi.userservice.service;


import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserLookupResponseDTO;
import com.nihalramtripathi.userservice.dto.UserRegistrationRequestDTO;
import com.nihalramtripathi.userservice.dto.UserResponseDTO;


public interface UserService {
     UserResponseDTO register(UserRegistrationRequestDTO registrationDTO);
     UserLookupResponseDTO checkUserLogin(CommonRequestDTO commonRequestDTO);
}

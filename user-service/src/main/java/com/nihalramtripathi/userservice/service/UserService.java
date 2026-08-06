package com.nihalramtripathi.userservice.service;


import com.nihalramtripathi.userservice.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;


public interface UserService {
     void  register(UserRegistrationDTO registrationDTO);
}

package com.nihalramtripathi.userservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String mobileNumber;

    private String email;

    private String fullName;

    private String fatherName;

    private Boolean salaried;

}

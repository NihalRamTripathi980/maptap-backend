package com.nihalramtripathi.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private Long userId;

    private String mobileNumber;

    private String email;

    private String fullName;

    private Boolean salaried;

}

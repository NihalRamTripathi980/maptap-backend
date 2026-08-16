package com.nihalramtripathi.authservice.dto.response;

import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MobileCheckResponseDTO {

    private boolean userExists;

    private String nextStep;

    private String mobileNumber;

    private String message;

    private UserDetailsResponseDTO userDetails;
}

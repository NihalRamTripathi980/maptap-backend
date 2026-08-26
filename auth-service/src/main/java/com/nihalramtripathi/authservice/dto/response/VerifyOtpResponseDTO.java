package com.nihalramtripathi.authservice.dto.response;

import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyOtpResponseDTO {

    private String message;
    private Boolean status;
    private String accessToken;
    private UserDetailsResponseDTO userDetails;
}

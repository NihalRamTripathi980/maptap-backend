package com.nihalramtripathi.authservice.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {


    private Long userId;
    private String mobileNumber;
    private String accessToken;

    private String tokenType;
    private long expiresIn;


}

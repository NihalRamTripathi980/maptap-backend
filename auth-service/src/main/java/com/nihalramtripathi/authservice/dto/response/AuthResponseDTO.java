package com.nihalramtripathi.authservice.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    private String accessToken;

    private String tokenType;

    private Long userId;
}

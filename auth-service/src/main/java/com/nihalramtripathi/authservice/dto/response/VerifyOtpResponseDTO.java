package com.nihalramtripathi.authservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyOtpResponseDTO {

    private String message;
    private Boolean status;
}

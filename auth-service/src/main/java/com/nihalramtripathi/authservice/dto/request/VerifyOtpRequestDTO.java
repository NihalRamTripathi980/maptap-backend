package com.nihalramtripathi.authservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyOtpRequestDTO {

    private String mobileNumber;
    private String otp;
}

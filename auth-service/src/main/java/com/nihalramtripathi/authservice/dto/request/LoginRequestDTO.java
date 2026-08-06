package com.nihalramtripathi.authservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String passcode;
}

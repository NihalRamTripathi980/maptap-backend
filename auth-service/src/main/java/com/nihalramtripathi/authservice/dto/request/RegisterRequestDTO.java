package com.nihalramtripathi.authservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String mobileNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String fullName;

    private String fatherName;

    private Boolean salaried;
}

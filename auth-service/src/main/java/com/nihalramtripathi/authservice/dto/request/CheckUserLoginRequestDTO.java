package com.nihalramtripathi.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckUserLoginRequestDTO {

    @NotBlank
    private String mobileNumber;
}

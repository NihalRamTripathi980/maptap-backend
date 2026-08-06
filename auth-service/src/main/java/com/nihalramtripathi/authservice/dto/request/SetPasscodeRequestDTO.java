package com.nihalramtripathi.authservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetPasscodeRequestDTO {

    @NotNull
    private Long userId;

    @Pattern(regexp="^\\d{4}$")
    private String passcode;
}

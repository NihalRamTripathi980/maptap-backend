package com.nihalramtripathi.commoncore.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponseDTO {

    private Long userId;

    private String userName;

    private String mobileNumber;
}

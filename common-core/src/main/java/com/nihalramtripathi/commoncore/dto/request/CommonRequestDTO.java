package com.nihalramtripathi.commoncore.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRequestDTO {

    private Long userId;

    private String userName;

    private String mobileNumber;

}

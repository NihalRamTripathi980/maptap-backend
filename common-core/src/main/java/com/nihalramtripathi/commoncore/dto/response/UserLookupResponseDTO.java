package com.nihalramtripathi.commoncore.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLookupResponseDTO {
    private boolean exists;
    private Long userId;
    private String userName;
}

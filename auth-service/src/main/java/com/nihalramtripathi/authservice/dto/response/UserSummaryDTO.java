package com.nihalramtripathi.authservice.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSummaryDTO {

    private Long userId;

    private String userName;
}

package com.nihalramtripathi.commoncore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequestDTO {

    private Long userId;

    private String userName;

    private String mobileNumber;

}

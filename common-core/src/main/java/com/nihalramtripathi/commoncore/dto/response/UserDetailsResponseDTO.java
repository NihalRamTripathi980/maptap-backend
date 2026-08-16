package com.nihalramtripathi.commoncore.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponseDTO  {
    private Long userId;

    private String userName;

    private String mobileNumber;
    private String email;

    private String fullName;

    private Boolean salaried;

}


package com.nihalramtripathi.commoncore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDTO {

    private String code;

    private String message;

    private int status;

    private String path;

    private String correlationId;

    private LocalDateTime timestamp;
}

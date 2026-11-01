package com.nihalramtripathi.loggingservice.dto.request;

import java.time.Instant;

public record ApiLogRequestDTO(
         String correlationId,
         String method,
         String path,
         String routeId,
         Integer statusCode,
         Long durationMs,
         Instant timestamp
) {
}

package com.nihalramtripathi.apigateway.kafka.logging;

import java.time.Instant;

public record ApiLogEvent(
        String correlationId,
        String method,
        String path,
        String routeId,
        Integer statusCode,
        long durationMs,
        Instant timestamp
) {}

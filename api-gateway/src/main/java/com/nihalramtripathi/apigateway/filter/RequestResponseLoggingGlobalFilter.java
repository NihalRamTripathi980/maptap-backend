package com.nihalramtripathi.apigateway.filter;

import com.nihalramtripathi.apigateway.kafka.logging.ApiLogEvent;
import com.nihalramtripathi.apigateway.kafka.logging.KafkaLogPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

@Component
public class RequestResponseLoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger log =
            LoggerFactory.getLogger(
                    RequestResponseLoggingGlobalFilter.class);

    private final KafkaLogPublisher kafkaLogPublisher;

    public RequestResponseLoggingGlobalFilter(
            KafkaLogPublisher kafkaLogPublisher) {
        this.kafkaLogPublisher = kafkaLogPublisher;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = UUID.randomUUID().toString();
        String method = exchange.getRequest().getMethod().name();
        String path = exchange.getRequest().getURI().getPath();

        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        String routeId = route != null ? route.getId() : "unknown";

        long startTime = System.nanoTime();

        // Propagate the generated correlation ID downstream.
        ServerWebExchange updatedExchange = exchange.mutate()
                .request(request -> request.headers(headers ->
                        headers.set("X-Correlation-ID", correlationId)))
                .build();

        // Return the correlation ID to the client.
        updatedExchange.getResponse().getHeaders()
                .set("X-Correlation-ID", correlationId);

        log.info(
                "Incoming request: correlationId={}, method={}, path={}, route={}",
                correlationId, method, path, routeId);

        return chain.filter(updatedExchange)
                .doFinally(signal -> {
                    long durationMs =
                            (System.nanoTime() - startTime) / 1_000_000;

                    HttpStatusCode status =
                            updatedExchange.getResponse().getStatusCode();

                    Integer statusCode =
                            status != null ? status.value() : null;

                    ApiLogEvent event = new ApiLogEvent(
                            correlationId,
                            method,
                            path,
                            routeId,
                            statusCode,
                            durationMs,
                            Instant.now()
                    );

                    log.info(
                            "Completed request: correlationId={}, status={}, durationMs={}",
                            correlationId, statusCode, durationMs);

                    kafkaLogPublisher.publish(event);
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

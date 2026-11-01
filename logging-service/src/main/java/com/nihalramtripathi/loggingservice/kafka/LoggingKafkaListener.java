package com.nihalramtripathi.loggingservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihalramtripathi.loggingservice.dto.request.ApiLogRequestDTO;
import com.nihalramtripathi.loggingservice.service.ApiLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingKafkaListener {

    private final ObjectMapper objectMapper;
    private final ApiLogService apiLogService;

    @KafkaListener(
            topics = "${app.kafka.logging.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String message) {

        try {
           // JsonNode logEvent = objectMapper.readTree(message);


            ApiLogRequestDTO event = objectMapper.readValue(
                    message,
                    ApiLogRequestDTO.class
            );

            log.info("Received API log event from Kafka");
            apiLogService.saveLog(event);

        } catch (Exception exception) {
            log.error("Failed to process API log message", exception);
            throw new RuntimeException("Failed to process API log", exception);
        }
    }
}
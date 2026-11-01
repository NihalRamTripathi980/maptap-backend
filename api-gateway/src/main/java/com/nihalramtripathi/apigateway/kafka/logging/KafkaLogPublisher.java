package com.nihalramtripathi.apigateway.kafka.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
public class KafkaLogPublisher {

    private static final Logger log =
            LoggerFactory.getLogger(KafkaLogPublisher.class);

    private final KafkaSender<String, String> sender;
    private final ObjectMapper objectMapper;
    private final String topic;

    public KafkaLogPublisher(
            KafkaSender<String, String> sender,
            ObjectMapper objectMapper,
            @Value("${app.logging.kafka-topic:api-logs}") String topic) {
        this.sender = sender;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public void publish(ApiLogEvent event) {
        final String json;

        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException ex) {
            log.error("Could not serialize API log event", ex);
            return;
        }

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, event.correlationId(), json);

        sender.send(Mono.just(
                        SenderRecord.create(record, event.correlationId())
                ))
                .doOnError(ex ->
                        log.error("Failed to publish API log event: {}",
                                event.correlationId(), ex))
                .subscribe(
                        result -> {
                            if (result.exception() != null) {
                                log.error("Kafka rejected API log event: {}",
                                        event.correlationId(),
                                        result.exception());
                            }
                        },
                        ex -> log.error("Kafka publishing failed", ex)
                );
    }
}
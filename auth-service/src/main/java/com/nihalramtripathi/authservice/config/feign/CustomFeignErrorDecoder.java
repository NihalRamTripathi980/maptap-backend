package com.nihalramtripathi.authservice.config.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihalramtripathi.commoncore.dto.response.ExceptionResponseDTO;
import com.nihalramtripathi.commoncore.exception.DownstreamServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public CustomFeignErrorDecoder() {
        this.objectMapper = new ObjectMapper();

        objectMapper.disable(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        ExceptionResponseDTO errorResponse =
                extractErrorResponse(response);

        log.error(
                "Feign error | method={} | status={} | code={} | message={} | correlationId={}",
                methodKey,
                response.status(),
                errorResponse.getCode(),
                errorResponse.getMessage(),
                errorResponse.getCorrelationId()
        );

        return new DownstreamServiceException(
                errorResponse.getCode(),
                errorResponse.getMessage(),
                response.status(),
                errorResponse.getCorrelationId()
        );
    }

    private ExceptionResponseDTO extractErrorResponse(
            Response response) {

        if (response.body() == null) {

            return ExceptionResponseDTO.builder()
                    .code("DOWNSTREAM_SERVICE_ERROR")
                    .message("Downstream service returned an empty response")
                    .status(response.status())
                    .build();
        }

        try (InputStream inputStream =
                     response.body().asInputStream()) {

            return objectMapper.readValue(
                    inputStream,
                    ExceptionResponseDTO.class
            );

        } catch (IOException exception) {

            log.error(
                    "Unable to parse Feign error response",
                    exception
            );

            return ExceptionResponseDTO.builder()
                    .code("DOWNSTREAM_SERVICE_ERROR")
                    .message("Unable to process downstream service error")
                    .status(response.status())
                    .build();
        }
    }
}
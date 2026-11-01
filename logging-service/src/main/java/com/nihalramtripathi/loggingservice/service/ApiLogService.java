package com.nihalramtripathi.loggingservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nihalramtripathi.loggingservice.dto.request.ApiLogRequestDTO;
import com.nihalramtripathi.loggingservice.entity.ApiLogsEntity;
import com.nihalramtripathi.loggingservice.repository.ApiLogsRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiLogService {

    private final ApiLogsRepository apiLogsRepository;

    ApiLogService(ApiLogsRepository apiLogsRepository){
        this.apiLogsRepository=apiLogsRepository;
    }

    public void saveLog(ApiLogRequestDTO requestDTO) {
        ApiLogsEntity apiLogsEntity = ApiLogsEntity.builder()
                .correlationId(requestDTO.correlationId())
                .method(requestDTO.method())
                .path(requestDTO.path())
                .routeId(requestDTO.routeId())
                .statusCode(requestDTO.statusCode())
                .durationMs(requestDTO.durationMs())
                .timestamp(requestDTO.timestamp())
                .build();
        apiLogsRepository.save(apiLogsEntity);

        System.out.println("Processing API log: " + apiLogsEntity.toString());
    }
}
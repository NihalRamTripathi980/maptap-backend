package com.nihalramtripathi.authservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestResponseLoggingFilter extends OncePerRequestFilter {


    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper wrappedRequest =
                new ContentCachingRequestWrapper(request, 1024 * 1024);

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(response);

        try {

            log.info(
                    "Before  doFilter auth-service :{}", wrappedResponse.getContentAsByteArray()
            );

            // =========================
            // REQUEST
            // =========================


            filterChain.doFilter(wrappedRequest, wrappedResponse);

        } finally {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            // =========================
            // REQUEST BODY
            // =========================

            String requestBody =
                    new String(
                            wrappedRequest.getContentAsByteArray(),
                            StandardCharsets.UTF_8
                    );

            // =========================
            // RESPONSE BODY
            // =========================

            String responseBody =
                    new String(
                            wrappedResponse.getContentAsByteArray(),
                            StandardCharsets.UTF_8
                    );

            // =========================
            // REQUEST LOG
            // =========================

            Map<String, Object> requestLog =
                    new LinkedHashMap<>();

            requestLog.put("type", "REQUEST");
            requestLog.put("method", request.getMethod());
            requestLog.put("uri", request.getRequestURI());
            requestLog.put("query", request.getQueryString());
            requestLog.put("body", requestBody);

            log.info(
                    "REQUEST {}",
                    objectMapper.writeValueAsString(requestLog)
            );

            // =========================
            // RESPONSE LOG
            // =========================

            Map<String, Object> responseLog =
                    new LinkedHashMap<>();

            responseLog.put("type", "RESPONSE");
            responseLog.put("method", request.getMethod());
            responseLog.put("uri", request.getRequestURI());
            responseLog.put("status", response.getStatus());
            responseLog.put("body", responseBody);
            responseLog.put("executionTimeMs", executionTime);

            log.info(
                    "RESPONSE {}",
                    objectMapper.writeValueAsString(responseLog)
            );

            // IMPORTANT
            wrappedResponse.copyBodyToResponse();
        }
    }
}
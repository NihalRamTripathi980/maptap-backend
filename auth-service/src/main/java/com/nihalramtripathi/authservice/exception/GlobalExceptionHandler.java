package com.nihalramtripathi.authservice.exception;

import com.nihalramtripathi.commoncore.dto.ApiResponse;
import com.nihalramtripathi.commoncore.dto.response.ExceptionResponseDTO;
import com.nihalramtripathi.commoncore.exception.BaseApplicationException;
import com.nihalramtripathi.commoncore.exception.DownstreamServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(
            BadCredentialsException ex) {

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("Invalid credentials")
                .data(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(BaseApplicationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleApplicationException(
            BaseApplicationException ex,
            HttpServletRequest request) {

        String correlationId = MDC.get("correlationId");

        log.error(
                "Application exception | code={} | status={} | path={} | correlationId={}",
                ex.getCode(),
                ex.getStatus(),
                request.getRequestURI(),
                correlationId,
                ex
        );

        ExceptionResponseDTO response = ExceptionResponseDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .status(ex.getStatus())
                .path(request.getRequestURI())
                .correlationId(correlationId)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> customExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DownstreamServiceException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDownstreamException(
            DownstreamServiceException exception,
            HttpServletRequest request) {

        log.error(
                "Downstream service exception | code={} | message={} | correlationId={}",
                exception.getCode(),
                exception.getMessage(),
                exception.getCorrelationId()
        );

        ExceptionResponseDTO response =
                ExceptionResponseDTO.builder()
                        .code(exception.getCode())
                        .message(exception.getMessage())
                        .status(exception.getStatus())
                        .path(request.getRequestURI())
                        .correlationId(exception.getCorrelationId())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }
}

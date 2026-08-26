package com.nihalramtripathi.authservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.jspecify.annotations.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@Component
@Aspect
public class RequestResponseInterceptor implements HandlerInterceptor{

    public static final String CORRELATION_ID = "X-Correlation-Id";
    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);

        String correlationId = request.getHeader(CORRELATION_ID);

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        request.setAttribute(CORRELATION_ID, correlationId);
        response.setHeader(CORRELATION_ID, correlationId);

        log.info(
                "Incoming request | method={} | uri={} | correlationId={} | clientIp={}",
                request.getMethod(),
                request.getRequestURI(),
                correlationId,
                getClientIp(request)
        );

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable ModelAndView modelAndView) {

        log.debug(
                "Request processed | method={} | uri={} | status={} | correlationId={}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                request.getAttribute(CORRELATION_ID)
        );
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex) {

        Long startTime =
                (Long) request.getAttribute(START_TIME);

        long duration = 0;

        if (startTime != null) {
            duration = System.currentTimeMillis() - startTime;
        }

        String correlationId =
                (String) request.getAttribute(CORRELATION_ID);

        if (ex != null) {

            log.error(
                    "Request failed | method={} | uri={} | status={} | duration={}ms | correlationId={} | exception={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration,
                    correlationId,
                    ex.getClass().getSimpleName(),
                    ex
            );

        } else {

            log.info(
                    "Outgoing response | method={} | uri={} | status={} | duration={}ms | correlationId={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration,
                    correlationId
            );
        }
    }

    private String getClientIp(HttpServletRequest request) {

        String forwardedFor =
                request.getHeader("X-Forwarded-For");

        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }
}
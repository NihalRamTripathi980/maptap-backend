package com.nihalramtripathi.authservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Around("execution(* com.nihalramtripathi.authservice.service..*(..))")
    public Object logServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName =
                joinPoint.getSignature().toShortString();

        long start = System.currentTimeMillis();

        log.info("Service started | method={}", methodName);

        try {

            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - start;

            log.info(
                    "Service completed | method={} | duration={}ms",
                    methodName,
                    duration
            );

            return result;

        } catch (Exception ex) {

            long duration = System.currentTimeMillis() - start;

            log.error(
                    "Service failed | method={} | duration={}ms | error={}",
                    methodName,
                    duration,
                    ex.getMessage(),
                    ex
            );

            throw ex;
        }
    }
}

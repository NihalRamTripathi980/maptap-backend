package com.nihalramtripathi.authservice.config.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CustomFeignClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}
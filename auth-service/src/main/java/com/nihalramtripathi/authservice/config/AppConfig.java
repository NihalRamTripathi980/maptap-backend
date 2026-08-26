package com.nihalramtripathi.authservice.config;

import com.nihalramtripathi.authservice.interceptor.RequestResponseInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class AppConfig implements WebMvcConfigurer {


    final private RequestResponseInterceptor requestResponseInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestResponseInterceptor)
                .addPathPatterns("/auth/*")
                .excludePathPatterns("/actuator/**",
                        "/health",
                        "/swagger-ui/**");

    }
}

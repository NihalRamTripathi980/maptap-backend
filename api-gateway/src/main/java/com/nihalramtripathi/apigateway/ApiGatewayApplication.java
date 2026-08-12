package com.nihalramtripathi.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages ={
                "com.nihalramtripathi.apigateway",
                "com.nihalramtripathi.commonsecurity",
                "com.nihalramtripathi.commoncore",
        }
)
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

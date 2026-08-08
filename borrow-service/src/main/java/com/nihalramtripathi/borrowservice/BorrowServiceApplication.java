package com.nihalramtripathi.borrowservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.nihalramtripathi.borrowservice",
                "com.nihalramtripathi.commonsecurity",
                "com.nihalramtripathi.commoncore",
        }
)
public class BorrowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowServiceApplication.class, args);
    }

}

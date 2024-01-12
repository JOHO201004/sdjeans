package com.sdjeans.sdjeans_app.C_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CApplication {

    public static void main(String[] args) {
        SpringApplication.run(CApplication.class, args);
    }
}

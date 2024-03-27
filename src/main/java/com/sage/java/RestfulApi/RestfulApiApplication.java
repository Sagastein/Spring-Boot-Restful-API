package com.sage.java.RestfulApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulApiApplication {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(RestfulApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
        logger.info("Application started successfully");
    }
}

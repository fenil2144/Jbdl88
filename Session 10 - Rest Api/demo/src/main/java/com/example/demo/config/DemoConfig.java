package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {

    private static Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    DemoConfig() {
        System.out.println("DemoConfig initialized");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        logger.info("restTemplate created in DemoConfig: {}", restTemplate);
        return restTemplate;
    }
}

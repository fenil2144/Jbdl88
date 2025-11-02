package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    DemoConfig(){
        System.out.println("DemoConfig initialized");
    }
}

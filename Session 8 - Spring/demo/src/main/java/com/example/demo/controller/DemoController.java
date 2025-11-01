package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoApplication;
import com.example.demo.models.Demo;

@RestController
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    Demo demoObj;

    public DemoController() {
        logger.info("DemoController initialized");
    }

    @GetMapping()
    public void helloWorld() {
        Demo demo = new Demo();
        logger.info("Demo Object created: {}", demo);
        logger.info("Autowired Demo Object: {}", demoObj);
    }

    // com.example.demo.models.Demo@674b752f
    // com.example.demo.models.Demo@6ca11472

    // Demo Object created: com.example.demo.models.Demo@778d0155
    // Autowired Demo Object: com.example.demo.models.Demo@56542ae0

    // Demo Object created: com.example.demo.models.Demo@3543099
    // Autowired Demo Object: com.example.demo.models.Demo@56542ae0
}

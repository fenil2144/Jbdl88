package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Demo;

@RestController
public class DemoController2 {

    @Autowired
    Demo demoObj2;

    @GetMapping("/2")
    public void helloWorld2() {
        System.out.println("Autowired Demo Object in DemoController2: " + demoObj2);
    }
}

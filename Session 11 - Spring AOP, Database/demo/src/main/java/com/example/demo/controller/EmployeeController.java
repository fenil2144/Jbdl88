package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.EmployeeRequest;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    // /api/employee - POST - C
    // /api/employee/{id} - GET - R
    // /api/employee/{id} - PUT - U
    // /api/employee/{id} - DELETE - D

    @GetMapping("/request-param")
    public void getEmployeeByIdUsingRequestParam(@RequestParam("id") int employeeId,
                                                 @RequestParam(required = false, defaultValue = "General") String department) {
        logger.info("Employee ID received as Request Param: {}", employeeId);
    }

    @GetMapping(value = {"/path-variable/{id}/details/{detailsId}", "/pv/{id}/details/{detailsId}"})
    public void getEmployeeByIdUsingPathVariable(@PathVariable("id") int employeeId,
                                                 @PathVariable("detailsId") int detailsId) {
        logger.info("Employee id received as Path Variable: {}", employeeId);
    }

    @GetMapping("/request-body")
    public void getEmployeeByIdUsingRequestBody(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("Employee request body received: {}", employeeRequest);
    }

    @PostMapping("/request-param-post")
    public void getEmployeeByIdPostUsingRequestParam(@RequestParam int employeeId,
                                                     @RequestParam(required = false, defaultValue = "General") String department) {
        logger.info("Employee ID received as Request Param: {}", employeeId);
    }

    @PostMapping("/request-body")
    public void getEmployeeByIdUsingRequestBody2(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("Employee request body received: {}", employeeRequest);
    }

    @GetMapping("/rest-template")
    public void getRestTemplate() {
        logger.info("Rest Template endpoint called with restTemplate: {}", restTemplate);
    }

}

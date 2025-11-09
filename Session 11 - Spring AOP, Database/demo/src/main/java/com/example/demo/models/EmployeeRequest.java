package com.example.demo.models;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private int age;
    private String address;
    private long phoneNumber;
}
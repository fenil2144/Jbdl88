package com.example.crud_jdbc_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String dob;
}

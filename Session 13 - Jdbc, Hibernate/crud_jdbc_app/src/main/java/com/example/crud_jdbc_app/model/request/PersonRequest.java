package com.example.crud_jdbc_app.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import com.example.crud_jdbc_app.model.Person;

@Data
public class PersonRequest {

    @Positive
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String dob;

    public Person to(){
        return Person.builder().id(id).firstName(firstName).lastName(lastName)
                .dob(dob).build();
    }
}

package com.example.elibrary.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import com.example.elibrary.models.Student;
import com.example.elibrary.models.enums.AccountStatus;

@Data
public class StudentCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;
    private String address;

    public Student toStudent(){
        return Student.builder()
                .name(name).email(email).phone(phone).address(address)
                .accountStatus(AccountStatus.ACTIVE)
                .build();

    }

}
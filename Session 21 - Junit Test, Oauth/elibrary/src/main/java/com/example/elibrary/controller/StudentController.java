package com.example.elibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.models.Student;
import com.example.elibrary.models.request.StudentCreateRequest;
import com.example.elibrary.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        return new ResponseEntity<>(studentService.saveStudent(studentCreateRequest), HttpStatus.CREATED);
    }

    // Add rest of the API's for student - update-student, delete-student, get-student-by-id etc.

}

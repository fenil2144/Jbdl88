package com.example.elibrary.service;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.elibrary.models.Student;
import com.example.elibrary.models.request.StudentCreateRequest;
import com.example.elibrary.repository.StudentRepositoryInterf;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepositoryInterf studentRepositoryInterf;


    public Student saveStudent(StudentCreateRequest studentCreateRequest) {

        Student student = studentCreateRequest.toStudent();
        // Add validations whatever applicable
        return studentRepositoryInterf.save(student);
    }

    public Optional<Student> getStudent(int studentId){
        return studentRepositoryInterf.findById(studentId);
    }
}

package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elibrary.models.Student;

@Repository
public interface StudentRepositoryInterf extends JpaRepository<Student, Integer> {
}

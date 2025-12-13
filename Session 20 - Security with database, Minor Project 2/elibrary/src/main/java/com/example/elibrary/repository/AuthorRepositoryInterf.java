package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elibrary.models.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);
}

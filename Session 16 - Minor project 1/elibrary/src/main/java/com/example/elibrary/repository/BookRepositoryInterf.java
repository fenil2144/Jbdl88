package com.example.elibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elibrary.models.Book;

@Repository
public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String name);
    List<Book> findByAuthor_name(String authorName);
    List<Book> findByGenre(String genre);
    List<Book> findByCost(int cost);
}

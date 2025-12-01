package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.models.Book;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.Transaction;
import com.example.elibrary.models.enums.TransactionType;

public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);
}

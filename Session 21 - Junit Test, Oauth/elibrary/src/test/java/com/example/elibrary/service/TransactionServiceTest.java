package com.example.elibrary.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import com.example.elibrary.exception.TransactionServiceException;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.Transaction;
import com.example.elibrary.models.enums.AccountStatus;
import com.example.elibrary.models.enums.Genre;
import com.example.elibrary.models.enums.TransactionType;
import com.example.elibrary.repository.TransactionRepositoryInterf;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepositoryInterf transactionRepositoryInterf;

    @InjectMocks
    TransactionService transactionService;

//    @BeforeEach
//    public void print() {
//        log.debug("In Before Each");
//    }

    @Test
    @DisplayName("Test1: Perform Issue Transaction")
    @Order(1)
    public void transact_test() {

        int bookId = 1;
        int studentId = 1;
        int transactionId = 1;
        String externalTransactionId = UUID.randomUUID().toString();

        Optional<Student> studentOptional = Optional.ofNullable(Student.builder()
                .id(studentId).name("James").email("james@gmail.com")
                .phone("98787676709").address("India").accountStatus(AccountStatus.ACTIVE).build());

        Optional<Book> bookOptional = Optional.ofNullable(Book.builder()
                .id(bookId).title("Intro to Java").genre(Genre.TECHNOLOGY).cost(600).build());

        Transaction transaction = Transaction.builder()
                .id(transactionId).externalId(externalTransactionId).transactionType(TransactionType.ISSUE)
                .student(studentOptional.get()).book(bookOptional.get())
                .build();

        when(studentService.getStudent(anyInt())).thenReturn(studentOptional);
        when(bookService.findBookById(anyInt())).thenReturn(bookOptional);
        when(transactionRepositoryInterf.save(any())).thenReturn(transaction);

        String transactionIdReturned = transactionService.transact(studentId, bookId, TransactionType.ISSUE.name());

        // Verify that transaction id should not be empty
        Assertions.assertFalse(transactionIdReturned.isBlank());

        // Verifies the number of times particular method is called
        Mockito.verify(studentService, times(1)).getStudent(anyInt());
    }

    @Test
    public void transact_transactionServiceException() {
        int bookId = 1;
        int studentId = 1;

        Optional<Student> studentOptional = Optional.ofNullable(Student.builder()
                .id(studentId).name("James").email("james@gmail.com")
                .phone("98787676709").address("India").accountStatus(AccountStatus.ACTIVE).build());

        when(studentService.getStudent(anyInt())).thenReturn(studentOptional);
        when(bookService.findBookById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionServiceException.class, () ->{
            transactionService.transact(bookId, studentId, TransactionType.ISSUE.name());
        });


    }
}

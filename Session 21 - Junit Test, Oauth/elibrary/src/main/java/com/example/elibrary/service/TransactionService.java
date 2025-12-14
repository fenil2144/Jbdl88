package com.example.elibrary.service;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.elibrary.exception.TransactionServiceException;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.Transaction;
import com.example.elibrary.models.enums.TransactionType;
import com.example.elibrary.repository.TransactionRepositoryInterf;

@Service
@Transactional
public class TransactionService {

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    // Two types of transaction which are supported:
    //    1) Issue:
    //        - Check if a student is valid
    //        - Check if a book is valid
    //        - Check if a book is available
    //        - Make the book to student transaction
    //        - Make the book unavailable for others
    //    2) Return:
    //        - Check if a student is valid
    //        - Check if a book is valid
    //        - Check if the book is issued to the same student
    //        - Make the transaction
    //        - Make the book available for others
    public String transact(int studentId, int bookId, String transactionType) {
        // studentRepositoryInterf.findById(studentId); - Not recommended
        Optional<Student> student = studentService.getStudent(studentId);
        // Validate student
        if (student.isEmpty()) {
            throw new TransactionServiceException("Student is not valid");
        }

        Optional<Book> bookById = bookService.findBookById(bookId);
        // validate book
        if (bookById.isEmpty()) {
            throw new TransactionServiceException("Book is not valid");
        }

        return switch (transactionType) {
            case "ISSUE" -> issueBookTransaction(student, bookById);
            case "RETURN" -> returnBookTransaction(student, bookById);
            default -> throw new TransactionServiceException("Transaction type is not supported");
        };
    }

    private String issueBookTransaction(Optional<Student> student, Optional<Book> bookById) {
        // Check if a book is available
        if (bookById.get().getStudent() != null) {
            throw new TransactionServiceException("Book is not available");
        }

        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment((double) bookById.get().getCost())
                .book(bookById.get()).student(student.get())
                .build();

        transactionRepositoryInterf.save(transaction);
//        if (transaction.getPayment() > 10) {
//            throw new RuntimeException("Server encountered error");
//        }
        // Make the book unavailable for others
        bookById.get().setStudent(student.get());
        bookService.save(bookById.get());

        return transaction.getExternalId();
    }


    private String returnBookTransaction(Optional<Student> student, Optional<Book> bookById) {

        // Check if the book is already issued
        if (bookById.get().getStudent() == null) {
            throw new TransactionServiceException("Book is not issues to any student");
        }

        // Check if a book is issued to the same student
        if (bookById.get().getStudent().getId() != student.get().getId()) {
            throw new TransactionServiceException("Book is issued to different student");
        }

        Transaction issueTransaction = transactionRepositoryInterf.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
                bookById.get(), student.get(), TransactionType.ISSUE);

        // Calculate fine and make the return transaction
        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .payment((double) (bookById.get().getCost() - calculateFine(issueTransaction)))
                .book(bookById.get()).student(student.get())
                .build();
        transactionRepositoryInterf.save(transaction);

        // Make the book available for others
        bookById.get().setStudent(null);
        bookService.save(bookById.get());

        return transaction.getExternalId();
    }

    private long calculateFine(Transaction issueTransaction) {
        long bookIssueTime = issueTransaction.getCreatedOn().getTime();
        long bookReturnTime = System.currentTimeMillis();

        long diffInMillis = bookReturnTime - bookIssueTime;
        long daysPassed = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if (daysPassed > 15) {
            return (daysPassed - 15) * 10L;
        }
        return 0;
    }

}

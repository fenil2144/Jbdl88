package com.example.elibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/{transactionType}")
    public ResponseEntity transact(@RequestParam("studentId") int studentId,
                                   @RequestParam("bookId") int bookId,
                                   @PathVariable("transactionType") String transactionType) {
        return new ResponseEntity(transactionService.transact(studentId, bookId, transactionType),
                HttpStatus.CREATED);
    }
}

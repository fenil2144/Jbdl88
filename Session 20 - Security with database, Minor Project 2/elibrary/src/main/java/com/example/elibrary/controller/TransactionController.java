package com.example.elibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.models.MyUser;
import com.example.elibrary.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/{transactionType}")
    public ResponseEntity transact(@RequestParam("bookId") int bookId,
                                   @PathVariable("transactionType") String transactionType) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

        if (myUser.getStudent() == null) {
            throw new RuntimeException("User is not a student");
        }
        return new ResponseEntity(transactionService.transact(myUser.getStudent().getId(), bookId, transactionType),
                HttpStatus.CREATED);
    }
}

package com.example.hibernate.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate.demo.model.Account;
import com.example.hibernate.demo.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity addAccount(@RequestBody Account account) {
        Account account1 = accountService.addAccount(account);
        return new ResponseEntity(account1, HttpStatus.OK);
    }

}

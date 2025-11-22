package com.example.hibernate.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{accountId}")
    public ResponseEntity getAccountByid(@PathVariable int accountId) {
        return new ResponseEntity(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity updateAccountById(@PathVariable int accountId, @RequestBody Account account){
        return new ResponseEntity(accountService.updateAccountById(accountId, account), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity deleteAccountById(@PathVariable int accountId){
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    }
}

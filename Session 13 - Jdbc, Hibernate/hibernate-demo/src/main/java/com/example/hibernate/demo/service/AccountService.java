package com.example.hibernate.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernate.demo.model.Account;
import com.example.hibernate.demo.repository.AccountRepositoryInterf;

@Service
public class AccountService {
    @Autowired
    AccountRepositoryInterf accountRepositoryInterf;

    public Account addAccount(Account account) {
        return accountRepositoryInterf.save(account);
    }
}

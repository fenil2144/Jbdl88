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

    public Account getAccountById(int accountId) {
        return  accountRepositoryInterf.findById(accountId).orElse(null);
    }

    public Account updateAccountById(int accountId, Account account) {
        account.setId(accountId);
        return accountRepositoryInterf.save(account);
    }

    public void deleteAccountById(int accountId) {
        accountRepositoryInterf.deleteById(accountId);
    }
}

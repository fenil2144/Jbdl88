package com.example.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hibernate.demo.model.Account;

@Repository
public interface AccountRepositoryInterf extends JpaRepository<Account, Integer> {

    // JPQL query to fetch account holder names
    @Query(value = "select a from Account a")
    List<Account> getAllAccountholderName();

    // Native SQL query to fetch account holder names
    @Query(value = "select * from account", nativeQuery = true)
    List<Account> getAllAccounts();

    Account findByAccountHolderName(String accountHolderName);
}

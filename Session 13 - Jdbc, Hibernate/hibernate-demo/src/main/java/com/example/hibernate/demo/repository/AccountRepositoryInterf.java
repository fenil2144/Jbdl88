package com.example.hibernate.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hibernate.demo.model.Account;

@Repository
public interface AccountRepositoryInterf extends JpaRepository<Account, Integer> {
}

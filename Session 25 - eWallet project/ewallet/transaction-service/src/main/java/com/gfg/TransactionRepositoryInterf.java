package com.gfg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {
}

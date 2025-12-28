package com.gfg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepositoryInterf extends JpaRepository<Wallet, Integer> {

    Wallet findByPhoneNumber(String phoneNumber);

    @Query("update wallet w set w.balance = w.balance + ?2 where w.phoneNumber = ?")
    void updateWallet(String phoneNumber, Double amount);
}

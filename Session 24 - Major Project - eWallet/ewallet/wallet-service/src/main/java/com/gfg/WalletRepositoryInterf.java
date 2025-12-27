package com.gfg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepositoryInterf extends JpaRepository<Wallet, Integer> {

    Wallet findByPhoneNumber(String phoneNumber);
}

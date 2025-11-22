package com.example.hibernate.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_owner_name", nullable = false, length = 100, unique = true)
    private String accountHolderName;
    private String address;
    private String accountType;

    // For using composite key with hibernate
//    @EmbeddedId
//    private CompositeKey compositeKey;
}

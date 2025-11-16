package com.example.hibernate.demo.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accountHolderName;
    private int age;
    private String accountType;

    // For using composite key with hibernate
//    @EmbeddedId
//    private CompositeKey compositeKey;
}

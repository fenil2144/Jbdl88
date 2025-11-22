package com.example.hibernate.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FixedDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int amount;
}

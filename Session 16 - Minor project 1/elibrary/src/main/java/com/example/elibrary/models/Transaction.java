package com.example.elibrary.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.elibrary.models.enums.TransactionType;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalId;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private Double payment;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Student student;

    @CreationTimestamp
    private Date createdOn;
}

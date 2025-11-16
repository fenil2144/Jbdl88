package com.example.hibernate.demo.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompositeKey {

    private int id;
    private int age;
}

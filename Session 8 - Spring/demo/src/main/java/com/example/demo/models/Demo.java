package com.example.demo.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(scopeName = "prototype")
@Component
public class Demo {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

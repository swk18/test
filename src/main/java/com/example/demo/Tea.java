package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;
@JsonSerialize
class Tea {
    private final String id;
    private String name;

    public Tea(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tea(String name) {

        this(UUID.randomUUID().toString(), name);

    }

    public String getId() {
        return id;

    }

    public String getName() {
        return name;
    }

    public void setName() {

        this.name = "Черный";
    }

}
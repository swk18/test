package com.example.demo;

import tools.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MyError {
    private String text;

    public String getText() {
        return text;
    }

    public MyError(String text) {
        this.text = text;
    }
}

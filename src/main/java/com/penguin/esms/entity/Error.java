package com.penguin.esms.entity;

public class Error {
    private String message;

    public Error(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"message\":\"" + message + '\"' +
                '}';
    }
}

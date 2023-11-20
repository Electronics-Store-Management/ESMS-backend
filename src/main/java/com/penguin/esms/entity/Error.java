package com.penguin.esms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Error {
    private List<String> errors = new ArrayList<>();

    public Error(String message) {
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }

    public Error(String... messages) {
        this.errors = Arrays.stream(messages).toList();
    }

    public Error(List<String> messages) {
        this.errors = messages;
    }

    @Override
    public String toString() {
        return "{" +
                "\"errors\":[\"" + errors.get(0) + "\"]" +
                '}';
    }
}

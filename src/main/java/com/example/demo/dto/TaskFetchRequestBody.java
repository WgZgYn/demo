package com.example.demo.dto;

public record TaskFetchRequestBody(Account account) {
    public TaskFetchRequestBody(String username, String password) {
        this(new Account(username, password));
    }
}
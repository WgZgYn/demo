package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    String username;
    String password_hash;

    Account(String username, String password_hash) {
        this.username = username;
        this.password_hash = password_hash;
    }

    @JsonProperty
    String getUsername() {
        return username;
    }

    @JsonProperty
    String getPassword_hash() {
        return password_hash;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
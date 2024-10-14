package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskFetchRequestBody {
    Account account;

    public TaskFetchRequestBody() {
    }

    public TaskFetchRequestBody(String username, String password_hash) {
        account = new Account(username, password_hash);
    }

    @JsonProperty("account")
        // 可选：确保 JSON 属性名与字段名匹配
    Account getAccount() {
        return account;
    }

    void setAccount(Account account) {
        this.account = account;
    }
}
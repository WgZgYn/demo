package com.example.demo.dto;

public class Task {
    private String action;
    private String device_id;

    // 无参构造函数
    public Task() {
    }

    // 带参构造函数
    public Task(String action, String device_id) {
        this.action = action;
        this.device_id = device_id;
    }

    // Getter 和 Setter
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
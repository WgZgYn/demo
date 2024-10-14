package com.example.demo.dto;

import java.util.List;

public class TaskFetchResult {
    private String status;
    private String action;
    private List<Task> tasks;

    // 无参构造函数
    public TaskFetchResult() {
    }

    // 带参构造函数
    public TaskFetchResult(String status, String action, List<Task> tasks) {
        this.status = status;
        this.action = action;
        this.tasks = tasks;
    }

    // Getter 和 Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
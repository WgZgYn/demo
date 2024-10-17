package com.example.demo.dto;

import java.util.List;

public record TaskFetchResult(String status, String action, List<Task> tasks) {

}
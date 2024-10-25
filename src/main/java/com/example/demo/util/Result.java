package com.example.demo.util;

import lombok.Data;

@Data
public class Result {
    protected int code;
    protected String message;
    protected long timestamp;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static Result Ok() {
        return new Result(200, "success");
    }

    public static Result Err(String message) {
        return new Result(500, message);
    }
}

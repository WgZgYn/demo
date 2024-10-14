package com.example.demo.util;

import java.io.Serializable;

public class Response<T> extends Result implements Serializable {
    private final T data;

    public Response(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> Response<T> success(T date) {
        return new Response<>(200, "success", date);
    }

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message, null);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}

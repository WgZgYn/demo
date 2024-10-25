package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Response<T> extends Result {
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
}

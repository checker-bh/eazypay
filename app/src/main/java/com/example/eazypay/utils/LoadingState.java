package com.example.eazypay.utils;

public class LoadingState<T> {
    private Status status;
    private T data;
    private String message;

    public enum Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    private LoadingState(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> LoadingState<T> loading() {
        return new LoadingState<>(Status.LOADING, null, null);
    }

    public static <T> LoadingState<T> success(T data) {
        return new LoadingState<>(Status.SUCCESS, data, null);
    }

    public static <T> LoadingState<T> error(String msg) {
        return new LoadingState<>(Status.ERROR, null, msg);
    }

    public Status getStatus() { return status; }
    public T getData() { return data; }
    public String getMessage() { return message; }
}

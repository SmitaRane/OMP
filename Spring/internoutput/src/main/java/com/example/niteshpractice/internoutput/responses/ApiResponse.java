package com.example.niteshpractice.internoutput.responses;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private boolean success;
    private int status_code;
    private String message;
    private String url;
    private LocalDateTime timestamp;
    private T data;

    // ✅ Constructor
    public ApiResponse(boolean success, int status_code, String message, String url, T data) {
        this.success = success;
        this.status_code = status_code;
        this.message = message;
        this.url = url;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    // ✅ Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.nitish.zorvyn_assignment.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private String path;

    public ApiResponse(int status, String message, T data, HttpServletRequest request) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = request.getRequestURI();
    }

    public static<T> ApiResponse<T> ok(String message, T data, HttpServletRequest servletRequest){
        return new ApiResponse<>(HttpStatus.OK.value(), message, data, servletRequest);
    }

    public static<T> ApiResponse<T> created(String message, T data, HttpServletRequest servletRequest){
        return new ApiResponse<>(HttpStatus.CREATED.value(), message, data, servletRequest);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getPath() {
        return path;
    }
}

package com.nitish.zorvyn_assignment.dto.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private String path;

    public ApiResponse(int status, String message, T data, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = path;
    }
}

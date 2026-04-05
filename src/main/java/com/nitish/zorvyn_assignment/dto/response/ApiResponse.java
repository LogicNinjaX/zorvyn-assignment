package com.nitish.zorvyn_assignment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL) // remove null fields from response
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {

    @Schema(description = "Response timestamp", example = "2026-04-05T10:15:30")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "200")
    private int status;

    @Schema(description = "Response message", example = "Request successful")
    private String message;

    @Schema(description = "Response data")
    private T data;

    @Schema(description = "API path", example = "/api/users/1")
    private String path;

    private ApiResponse(int status, String message, T data, HttpServletRequest request) {
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

    public static<T> ApiResponse<T> of(HttpStatus status, String message, T data, HttpServletRequest servletRequest){
        return new ApiResponse<>(status.value(), message, data, servletRequest);
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

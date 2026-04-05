package com.nitish.zorvyn_assignment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard error response")
public class ErrorResponse {

    @Schema(description = "Timestamp of the error", example = "2026-04-05T10:15:30")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message", example = "Validation failed")
    private String message;

    @Schema(description = "API path", example = "/api/v1/users")
    private String path;

    @Schema(description =  "Detailed validation or error messages")
    private Map<String, String> details;

    private ErrorResponse(int status, String message, String path, Map<String, String> details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
        this.details = details;
    }

    public static ErrorResponse of(HttpStatus status, String message, HttpServletRequest servletRequest, Map<String, String> details){
        return new ErrorResponse(status.value(), message, servletRequest.getRequestURI(), details);
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

    public String getPath() {
        return path;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}

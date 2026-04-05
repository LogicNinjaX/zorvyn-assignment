package com.nitish.zorvyn_assignment.exception;


import com.nitish.zorvyn_assignment.dto.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest servletRequest) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        logger.error("Error occurred in ValidationExceptionHandler: ", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, "Bad request", servletRequest, errors));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> duplicateFieldHandler(DataIntegrityViolationException ex, HttpServletRequest servletRequest) {

        var responseMessage = getString(ex);

        logger.error("Error occurred in DuplicateFieldHandler: ", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                HttpStatus.CONFLICT,
                                "Duplicate input found",
                                servletRequest,
                                Collections.singletonMap("message", responseMessage)
                        )
                );
    }

    private static @NonNull String getString(DataIntegrityViolationException ex) {
        Throwable cause = ex.getRootCause();
        String responseMessage = "Duplicate value";

        if (cause != null) {
            String message = cause.getMessage();

            if (message.contains("uk_user_record_username")) {
                responseMessage = "User name already exists";
            } else if (message.contains("uk_user_record_email")) {
                responseMessage = "User email already exists";
            } else {
                responseMessage = message;
            }
        }
        return responseMessage;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentHandler(RuntimeException ex, HttpServletRequest servletRequest) {
        logger.error("Error occurred in IllegalArgumentHandler: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.of(
                                HttpStatus.BAD_REQUEST,
                                "Validation error",
                                servletRequest,
                                Collections.singletonMap("message", ex.getLocalizedMessage())
                        )
                );
    }

    @ExceptionHandler({TransactionNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundHandler(RuntimeException ex, HttpServletRequest servletRequest) {
        logger.error("Error occurred in EntityNotFoundHandler: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.of(
                                HttpStatus.NOT_FOUND,
                                "Entity not found",
                                servletRequest,
                                Collections.singletonMap("message", ex.getLocalizedMessage())
                        )
                );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage())
        );

        logger.error("Error occurred in HandleConstraintViolation: ", ex);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        "Validation failed",
                        request,
                        errors
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleInvalidJson: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        "Malformed JSON request",
                        request,
                        Map.of("error", ex.getMostSpecificCause().getMessage())
                ));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleAccessDenied: ", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(
                        HttpStatus.FORBIDDEN,
                        "Access denied",
                        request,
                        Map.of("error", "You do not have permission")
                ));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuth(
            AuthenticationException ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleAuth: ", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(
                        HttpStatus.UNAUTHORIZED,
                        "Authentication failed",
                        request,
                        Map.of("error", ex.getMessage())
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleTypeMismatch: ", ex);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        "Type mismatch",
                        request,
                        Map.of(ex.getName(), "Invalid value")
                ));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NoHandlerFoundException ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleNotFound: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                        HttpStatus.NOT_FOUND,
                        "Endpoint not found",
                        request,
                        Map.of("error", ex.getRequestURL())
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {

        logger.error("Error occurred in HandleGeneric: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Internal server error",
                        request,
                        Map.of("error", ex.getMessage())
                ));
    }

}

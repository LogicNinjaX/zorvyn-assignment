package com.nitish.zorvyn_assignment.controller;


import com.nitish.zorvyn_assignment.dto.request.LoginRequest;
import com.nitish.zorvyn_assignment.dto.request.UserRegisterRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.LoginResponse;
import com.nitish.zorvyn_assignment.service.AuthService;
import jakarta.security.auth.message.AuthStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        var authToken = authService.login(request.username(), request.password());
        LoginResponse response = new LoginResponse(authToken, AuthStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.ok("Login successful", response, servletRequest));
    }

    @PostMapping(path = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody UserRegisterRequest request, HttpServletRequest servletRequest) {
        authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("User registration successful", null, servletRequest));
    }
}

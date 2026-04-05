package com.nitish.zorvyn_assignment.controller.doc;

import com.nitish.zorvyn_assignment.dto.request.LoginRequest;
import com.nitish.zorvyn_assignment.dto.request.UserRegisterRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication APIs", description = "APIs for user authentication and registration")
public interface AuthApiDoc {

    @Operation(
            summary = "User Login",
            description = "Authenticate user using username and password and return JWT token"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials",
                    content = @Content
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content
            )}
    )
    ResponseEntity<ApiResponse<LoginResponse>> login(LoginRequest request, HttpServletRequest servletRequest);


    @Operation(
            summary = "User Register",
            description = "Registers user details in the system"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "User name already exists",
                    content = @Content
            )
    })
    ResponseEntity<ApiResponse<Void>> register(UserRegisterRequest request, HttpServletRequest servletRequest);
}

package com.nitish.zorvyn_assignment.controller;

import com.nitish.zorvyn_assignment.dto.request.UpdateRoleRequest;
import com.nitish.zorvyn_assignment.dto.request.UpdateStatusRequest;
import com.nitish.zorvyn_assignment.dto.request.UserUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import com.nitish.zorvyn_assignment.service.UserService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserById
            (
                    @PathVariable UUID userId, HttpServletRequest servletRequest
            )
    {
        var response = userService.getUserById(userId);

        return ResponseEntity.ok(ApiResponse.ok("User fetched successfully", response, servletRequest));
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageResponse<UserDetailsResponse>>> getAllUsers
            (
                    Pageable pageable,
                    HttpServletRequest servletRequest
            )
    {
        var response = userService.getAllUsers(pageable);
        return ResponseEntity.ok(ApiResponse.ok("User fetched successfully", response, servletRequest));
    }


    @PatchMapping(path = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserUpdateResponse>> updateUserDetails
            (
                    @PathVariable UUID userId,
                    @RequestBody UserUpdateRequest request,
                    HttpServletRequest servletRequest
            )
    {
        var updateResponse = userService.updateUser(userId, request);

        return ResponseEntity.ok(ApiResponse.ok("User updated successfully", updateResponse, servletRequest));
    }


    @PatchMapping(path = "/{userId}/status", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserStatus
            (
                    @PathVariable UUID userId,
                    @RequestBody UpdateStatusRequest request
            )
    {
        userService.updateUserStatus(userId, request.status());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{userId}/role", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserRole
            (
                    @PathVariable UUID userId,
                    @RequestBody UpdateRoleRequest request
            )
    {
        userService.updateUserRole(userId, request.role());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser
            (
                    @PathVariable UUID userId
            )
    {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}

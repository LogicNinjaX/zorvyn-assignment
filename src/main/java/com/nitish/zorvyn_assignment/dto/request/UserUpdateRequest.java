package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;

public record UserUpdateRequest
        (
                String firstName,
                String lastName,
                String username,
                String email,
                String password,
                UserRole role,
                UserStatus status,
                Boolean deleted
        ) { }

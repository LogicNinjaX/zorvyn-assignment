package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;

import java.util.UUID;

public record UserUpdateResponse
        (
                UUID userId,
                String firstName,
                String lastName,
                String username,
                String email,
                UserRole role,
                UserStatus status,
                boolean deleted
        ) { }

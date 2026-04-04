package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDetailsResponse
        (
                UUID userId,
                String firstName,
                String lastName,
                String username,
                String email,
                UserRole role,
                UserStatus status,
                boolean deleted,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) { }

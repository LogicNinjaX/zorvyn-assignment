package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Detailed user response")
public record UserDetailsResponse
        (
                @Schema(description = "Unique user ID", example = "a1b2c3d4-5678-90ab-cdef-123456789abc")
                UUID userId,

                @Schema(description = "First name of the user", example = "Nitish")
                String firstName,

                @Schema(description = "Last name of the user", example = "Sahni")
                String lastName,

                @Schema(description = "Username", example = "nitish123")
                String username,

                @Schema(description = "Email address", example = "nitishsahni@gmail.com")
                String email,

                @Schema(description = "User role", example = "ROLE_ANALYST")
                UserRole role,

                @Schema(description = "User status", example = "ACTIVE")
                UserStatus status,

                @Schema(description = "Soft delete flag", example = "false")
                boolean deleted,

                @Schema(description = "Account creation time", example = "2026-04-05T10:15:30")
                LocalDateTime createdAt,

                @Schema(description = "Last updated time", example = "2026-04-05T10:20:30")
                LocalDateTime updatedAt
        ) { }

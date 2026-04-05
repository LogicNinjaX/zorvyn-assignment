package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response returned after updating a user")
public record UserUpdateResponse
        (
                @Schema(description = "Unique user ID", example = "a1b2c3d4-5678-90ab-cdef-123456789abc")
                UUID userId,

                @Schema(description = "First name", example = "Nitish")
                String firstName,

                @Schema(description = "Last name", example = "Sahni")
                String lastName,

                @Schema(description = "Username", example = "nitish123")
                String username,

                @Schema(description = "Email", example = "tuffy@gmail.com")
                String email,

                @Schema(description = "User role", example = "ROLE_VIEWER")
                UserRole role,

                @Schema(description = "User status", example = "ACTIVE")
                UserStatus status,

                @Schema(description = "Soft delete flag", example = "false")
                boolean deleted
        ) { }

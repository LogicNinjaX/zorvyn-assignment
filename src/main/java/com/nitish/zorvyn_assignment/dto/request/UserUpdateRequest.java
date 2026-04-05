package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for partially updating a user")
public record UserUpdateRequest
        (
                @Schema(description = "First name of the user", example = "Nitish")
                @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
                String firstName,

                @Schema(description = "Last name of the user", example = "Sahni")
                @Size(min = 2, max = 50)
                String lastName,

                @Schema(description = "Unique username", example = "nitish123")
                @Size(min = 4, max = 20)
                String username,

                @Schema(description = "User email address", example = "nitishsahni@gmail.com")
                @Email(message = "Invalid email format")
                String email,

                @Schema(
                        description = "User password",
                        example = "StrongPass@123",
                        accessMode = Schema.AccessMode.WRITE_ONLY
                )
                @Size(min = 8, message = "Password must be at least 8 characters")
                String password,

                @Schema(description = "Role of the user", example = "ROLE_ANALYST")
                UserRole role,

                @Schema(description = "Current status of the user", example = "ACTIVE")
                UserStatus status,

                @Schema(description = "Soft delete flag", example = "false")
                Boolean deleted
        ) { }

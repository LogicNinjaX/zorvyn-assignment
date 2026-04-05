package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "UserRegisterRequest",
        description = "Request payload for registering a new user"
)
public record UserRegisterRequest
        (
                @Schema(
                        description = "First name of the user",
                        example = "Nitish",
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
                @NotBlank(message = "First name is required")
                @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
                String firstName,

                @Schema(
                        description = "Last name of the user",
                        example = "Sahni"
                )
                @NotBlank(message = "Last name is required")
                @Size(min = 2, max = 50)
                String lastName,

                @Schema(
                        description = "Unique username",
                        example = "nitish123"
                )
                @NotBlank(message = "Username is required")
                @Size(min = 4, max = 20)
                String username,

                @Schema(
                        description = "User email address",
                        example = "nitishsahni@gmail.com"
                )
                @NotBlank(message = "Email is required")
                @Email(message = "Invalid email format")
                String email,

                @Schema(
                        description = "User password",
                        example = "StrongPass@123"
                )
                @NotBlank(message = "Password is required")
                @Size(min = 8, message = "Password must be at least 8 characters")
                String password,

                @Schema(
                        description = "Role of the user",
                        example = "ROLE_VIEWER"
                )
                UserRole role
        ) {
}

package com.nitish.zorvyn_assignment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "LoginRequest",
        description = "Request payload for user authentication"
)
public record LoginRequest(

        @NotBlank(message = "Username is required")
        @Size(max = 50, message = "Follow required username length")
        @Schema(
                description = "Username of the user",
                example = "nitish_sahni",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String username,

        @NotBlank(message = "Password is required.")
        @Schema(
                description = "User password",
                example = "StrongPass@123",
                accessMode = Schema.AccessMode.WRITE_ONLY,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password
) {
}

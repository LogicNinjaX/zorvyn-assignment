package com.nitish.zorvyn_assignment.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.security.auth.message.AuthStatus;

@Schema(description = "Response returned after successful authentication")
public record LoginResponse(

        @Schema(
                description = "JWT access token used for authenticated requests",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        )
        String token,

        @Schema(
                description = "Authentication status",
                example = "SUCCESS"
        )
        String authStatus
) { }

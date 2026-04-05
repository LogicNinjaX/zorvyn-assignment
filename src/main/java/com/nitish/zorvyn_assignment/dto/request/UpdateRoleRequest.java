package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload for updating user role")
public record UpdateRoleRequest
        (
                @Schema(
                        description = "New role of the user",
                        example = "ROLE_ADMIN"
                )
                @NotNull(message = "Role is required")
                UserRole role
        ) { }

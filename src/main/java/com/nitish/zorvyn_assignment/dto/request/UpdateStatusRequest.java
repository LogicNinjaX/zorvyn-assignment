package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload for updating user status")
public record UpdateStatusRequest
        (
                @Schema(
                        description = "New status of the user",
                        example = "ACTIVE"
                )
                @NotNull(message = "Status is required")
                UserStatus status
        ) { }

package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserStatus;

public record UpdateStatusRequest
        (
                UserStatus status
        ) { }

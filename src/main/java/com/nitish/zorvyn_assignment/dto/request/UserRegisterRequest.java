package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.UserRole;

public record UserRegisterRequest
        (
                String firstName,
                String lastName,
                String username,
                String email,
                String password,
                UserRole role
        ) {
}

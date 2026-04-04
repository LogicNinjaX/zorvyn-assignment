package com.nitish.zorvyn_assignment.dto.response;


import jakarta.security.auth.message.AuthStatus;

public record LoginResponse(
        String token,
        AuthStatus authStatus
) { }

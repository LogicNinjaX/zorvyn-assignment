package com.nitish.zorvyn_assignment.service;

import com.nitish.zorvyn_assignment.dto.request.UserRegisterRequest;

public interface AuthService {

    void registerUser(UserRegisterRequest request);

    String login(String username, String password);
}

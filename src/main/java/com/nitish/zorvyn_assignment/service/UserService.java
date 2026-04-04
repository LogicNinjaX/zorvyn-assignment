package com.nitish.zorvyn_assignment.service;

import com.nitish.zorvyn_assignment.dto.request.UserUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserUpdateResponse updateUser(UUID userId, UserUpdateRequest request);

    PageResponse<UserDetailsResponse> getAllUsers(Pageable pageable);

    UserDetailsResponse getUserById(UUID userId);

    void updateUserStatus(UUID userId, UserStatus status);

    void deleteUser(UUID userId);

    void updateUserRole(UUID userId, UserRole role);
}

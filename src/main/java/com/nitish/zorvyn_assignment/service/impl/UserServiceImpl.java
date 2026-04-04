package com.nitish.zorvyn_assignment.service.impl;

import com.nitish.zorvyn_assignment.dto.request.UserUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import com.nitish.zorvyn_assignment.exception.UserNotFoundException;
import com.nitish.zorvyn_assignment.repository.UserRepository;
import com.nitish.zorvyn_assignment.service.UserService;
import com.nitish.zorvyn_assignment.util.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Transactional
    @Override
    public UserUpdateResponse updateUser(UUID userId, UserUpdateRequest request){
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        patchDetailsIfProvided(user, request);

        user = userRepository.save(user);

        return userMapper.toUpdateResponse(user);
    }

    private void patchDetailsIfProvided(User user, UserUpdateRequest request){
        if(Objects.nonNull(request.firstName())) user.setFirstName(request.firstName());

        if(Objects.nonNull(request.lastName())) user.setLastName(request.lastName());

        if(Objects.nonNull(request.username())) user.setUsername(request.username());

        if(Objects.nonNull(request.email())) user.setEmail(request.email());

        if(Objects.nonNull(request.role())) user.setRole(request.role());

        if(Objects.nonNull(request.password())) user.setPassword(request.password()); // requires encryption in future

        if(Objects.nonNull(request.status())) user.setStatus(request.status());

        if(Objects.nonNull(request.deleted())) user.setDeleted(request.deleted());
    }

    @Override
    public PageResponse<UserDetailsResponse> getAllUsers(Pageable pageable){
        Page<UserDetailsResponse> detailsResponse = userRepository.findAllUsers(pageable)
                .map(userMapper::toDetailsResponse);

        return PageResponse.from(detailsResponse);
    }

    @Override
    public UserDetailsResponse getUserById(UUID userId){
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDetailsResponse(user);
    }

    @Transactional
    @Override
    public void updateUserStatus(UUID userId, UserStatus status){
        int updatedRows = userRepository.updateUserStatus(userId, status);
        if (updatedRows > 0) logger.info("User status updated successfully [user id={}, status={}]", userId, status);
    }

    @Transactional
    @Override
    public void deleteUser(UUID userId){
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!user.isDeleted()){
            user.setDeleted(true);
        }

        userRepository.save(user);
        logger.info("Soft deletion completed successfully [user id={}]", userId);
    }

    @Transactional
    @Override
    public void updateUserRole(UUID userId, UserRole role){
        int updatedRows = userRepository.updateUserRole(userId, role);

        if (updatedRows > 0) logger.info("User role updated successfully [user id={}, role={}]", userId, role);
    }
}

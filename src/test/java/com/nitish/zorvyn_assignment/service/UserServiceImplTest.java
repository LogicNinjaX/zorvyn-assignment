package com.nitish.zorvyn_assignment.service;

import com.nitish.zorvyn_assignment.dto.request.UserUpdateRequest;
import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import com.nitish.zorvyn_assignment.exception.UserNotFoundException;
import com.nitish.zorvyn_assignment.repository.UserRepository;
import com.nitish.zorvyn_assignment.service.impl.UserServiceImpl;
import com.nitish.zorvyn_assignment.util.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UUID userId;
    private User user;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        user = new User();
        user.setUserId(userId);
        user.setFirstName("Nitish");
        user.setDeleted(false);
    }


    @Test
    void updateUser_shouldThrowException_whenUserNotFound() {

        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(userId, new UserUpdateRequest(null,null,null,null,null,null,null,null)));
    }



    @Test
    void getUserById_shouldThrowException_whenNotFound() {

        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(userId));
    }

    @Test
    void updateUserStatus_shouldUpdateSuccessfully() {

        when(userRepository.updateUserStatus(userId, UserStatus.ACTIVE)).thenReturn(1);

        userService.updateUserStatus(userId, UserStatus.ACTIVE);

        verify(userRepository).updateUserStatus(userId, UserStatus.ACTIVE);
    }

    @Test
    void deleteUser_shouldSoftDeleteUser() {

        when(userRepository.findUserById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.deleteUser(userId);

        assertTrue(user.isDeleted());
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser_shouldThrowException_whenNotFound() {

        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUser(userId));
    }

    @Test
    void updateUserRole_shouldUpdateSuccessfully() {

        when(userRepository.updateUserRole(userId, UserRole.ROLE_ADMIN)).thenReturn(1);

        userService.updateUserRole(userId, UserRole.ROLE_ADMIN);

        verify(userRepository).updateUserRole(userId, UserRole.ROLE_ADMIN);
    }
}

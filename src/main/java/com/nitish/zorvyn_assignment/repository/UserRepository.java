package com.nitish.zorvyn_assignment.repository;

import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.enums.UserRole;
import com.nitish.zorvyn_assignment.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
            SELECT u FROM User u
            """)
    Page<User> findAllUsers(Pageable pageable);

    @Query("""
            SELECT u FROM User u
            WHERE u.userId = :userId
            """)
    Optional<User> findUserById(UUID userId);

    @Modifying
    @Query("""
            UPDATE User u
            SET u.status = :status
            WHERE u.userId = :userId
            """)
    int updateUserStatus(UUID userId, UserStatus status);

    @Modifying
    @Query("""
             UPDATE User u
             SET u.role = :role
             WHERE u.userId = :userId
            """)
    int updateUserRole(UUID userId, UserRole role);

    Optional<User> findByUsername(String username);
}

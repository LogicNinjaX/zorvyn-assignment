package com.nitish.zorvyn_assignment.controller.doc;

import com.nitish.zorvyn_assignment.dto.request.UpdateRoleRequest;
import com.nitish.zorvyn_assignment.dto.request.UpdateStatusRequest;
import com.nitish.zorvyn_assignment.dto.request.UserUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "User APIs", description = "APIs for managing users")
@SecurityRequirement(name = "bearerAuth")
public interface UserApiDoc {


    @Operation(summary = "Get User By ID", description = "Fetch user details using user ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User fetched successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<ApiResponse<UserDetailsResponse>> getUserById
            (
                    UUID userId, HttpServletRequest servletRequest
            );


    @Operation(summary = "Get All Users", description = "Fetch paginated list of users")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Users fetched successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<ApiResponse<PageResponse<UserDetailsResponse>>> getAllUsers
            (
                    @ParameterObject Pageable pageable,
                    HttpServletRequest servletRequest
            );


    @Operation(summary = "Update User", description = "Partially update user details")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<ApiResponse<UserUpdateResponse>> updateUserDetails
            (
                    UUID userId,
                    UserUpdateRequest request,
                    HttpServletRequest servletRequest
            );



    @Operation(summary = "Update User Status", description = "Update status of a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User status updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<Void> updateUserStatus
            (
                    UUID userId,
                    UpdateStatusRequest request
            );


    @Operation(summary = "Update User Role", description = "Update role of a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User role updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<Void> updateUserRole
            (
                    UUID userId,
                    UpdateRoleRequest request
            );

    @Operation(summary = "Update User Role", description = "Update role of a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<Void> deleteUser
            (
                    UUID userId
            );
}

package com.nitish.zorvyn_assignment.controller.doc;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.request.TransactionUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionCreateResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import com.nitish.zorvyn_assignment.security.CustomUserDetails;
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

@Tag(name = "Transaction APIs", description = "APIs for managing financial transactions")
@SecurityRequirement(name = "bearerAuth")
public interface TransactionApiDoc {

    @Operation(summary = "Create Transaction", description = "Create a new financial transaction")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Transaction created successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )

    })
    ResponseEntity<ApiResponse<TransactionCreateResponse>> createTransaction
            (
                    TransactionCreateRequest request,
                    CustomUserDetails userDetails,
                    HttpServletRequest servletRequest
            );


    @Operation(summary = "Get Transaction By ID", description = "Fetch transaction details using record ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Transaction fetched successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "403",
            description = "Forbidden - insufficient role",
            content = @Content
    )

    })
    ResponseEntity<ApiResponse<TransactionDetailsResponse>> getTransactionById
            (
                    UUID recordId,
                    HttpServletRequest servletRequest
            );


    @Operation(summary = "Get All Transactions", description = "Fetch paginated list of transactions")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Transactions fetched successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<ApiResponse<PageResponse<TransactionDetailsResponse>>> getAllTransactions
            (
                    @ParameterObject Pageable pageable,
                    HttpServletRequest servletRequest
            );


    @Operation(summary = "Update Transaction", description = "Partially update transaction details")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Transaction not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<Void> updateTransaction
            (
                    UUID recordId,
                    TransactionUpdateRequest request,
                    HttpServletRequest servletRequest
            );


    @Operation(summary = "Delete Transaction", description = "Soft delete a transaction")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Transaction not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )
    })
    ResponseEntity<Void> deleteTransaction
            (
                    UUID recordId,
                    HttpServletRequest servletRequest
            );
}

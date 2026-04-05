package com.nitish.zorvyn_assignment.controller;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.request.TransactionUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionCreateResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import com.nitish.zorvyn_assignment.security.CustomUserDetails;
import com.nitish.zorvyn_assignment.service.FinancialRecordService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {

    private final FinancialRecordService recordService;

    public TransactionController(FinancialRecordService recordService) {
        this.recordService = recordService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransactionCreateResponse>> createTransaction
            (
                    @RequestBody TransactionCreateRequest request,
                    @AuthenticationPrincipal CustomUserDetails userDetails,
                    HttpServletRequest servletRequest
            )
    {
        var response = recordService.createTransaction(userDetails.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Transaction record saved successfully", response, servletRequest));
    }


    @PreAuthorize("hasRole('ADMIN', 'ANALYST')")
    @GetMapping(path = "/{recordId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransactionDetailsResponse>> getTransactionById
            (
                    @PathVariable UUID recordId,
                    HttpServletRequest servletRequest
            )
    {
        var response = recordService.getTransactionById(recordId);
        return ResponseEntity.ok(ApiResponse.ok("Transaction details fetched successfully", response, servletRequest));
    }

    @PreAuthorize("hasRole('ADMIN', 'ANALYST')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageResponse<TransactionDetailsResponse>>> getAllTransactions
            (
                Pageable pageable,
                HttpServletRequest servletRequest
            )
    {
        var pageResponse = recordService.getAllTransactions(pageable);
        return ResponseEntity.ok(ApiResponse.ok("Transaction details fetched successfully", pageResponse, servletRequest));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{recordId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTransaction
            (
                    @PathVariable UUID recordId,
                    @RequestBody TransactionUpdateRequest request,
                    HttpServletRequest servletRequest
            )
    {
        recordService.updateTransactionRecord(recordId, request);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{recordId}")
    public ResponseEntity<Void> deleteTransaction
            (
                    @PathVariable UUID recordId,
                    HttpServletRequest servletRequest
            )
    {
        recordService.softDeleteTransaction(recordId);
        return ResponseEntity.noContent().build();
    }
}

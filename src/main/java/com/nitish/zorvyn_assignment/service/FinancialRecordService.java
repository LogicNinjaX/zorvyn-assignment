package com.nitish.zorvyn_assignment.service;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.request.TransactionUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionCreateResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FinancialRecordService {

    TransactionCreateResponse createTransaction(UUID userId, TransactionCreateRequest request);

    TransactionDetailsResponse getTransactionById(UUID recordId);

    PageResponse<TransactionDetailsResponse> getAllTransactions(Pageable pageable);

    void updateTransactionRecord(UUID recordId, TransactionUpdateRequest request);

    void softDeleteTransaction(UUID recordId);
}

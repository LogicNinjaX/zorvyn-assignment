package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.RecordType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionCreateResponse
        (
                UUID recordId,
                BigDecimal amount,
                RecordType type,
                String category,
                LocalDateTime timeStamp,
                String description,
                UUID createdBy,
                boolean deleted,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) { }

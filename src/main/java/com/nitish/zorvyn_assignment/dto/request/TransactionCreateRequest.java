package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.RecordType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionCreateRequest
        (
                BigDecimal amount,
                RecordType type,
                String category,
                LocalDateTime timeStamp,
                String description
        ) { }

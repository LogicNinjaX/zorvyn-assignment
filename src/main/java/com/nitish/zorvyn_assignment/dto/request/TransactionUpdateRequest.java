package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.Category;
import com.nitish.zorvyn_assignment.enums.RecordType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionUpdateRequest
        (
                BigDecimal amount,
                RecordType type,
                Category category,
                LocalDateTime timeStamp,
                String description,
                Boolean deleted
        ) { }

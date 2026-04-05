package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.Category;
import com.nitish.zorvyn_assignment.enums.RecordType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Detailed transaction response")
public record TransactionDetailsResponse
        (
                @Schema(description = "Unique transaction ID", example = "b3f1c2a4-9f2d-4c6a-8b1e-123456789abc")
                UUID recordId,

                @Schema(description = "Transaction amount", example = "1500.50")
                BigDecimal amount,

                @Schema(description = "Transaction amount", example = "1500.50")
                RecordType type,

                @Schema(description = "Transaction category", example = "FOOD")
                Category category,

                @Schema(description = "Transaction timestamp", example = "2026-04-05T10:15:30")
                LocalDateTime timeStamp,

                @Schema(description = "Transaction description", example = "Dinner with friends")
                String description,

                @Schema(description = "User who created the transaction", example = "a1b2c3d4-5678-90ab-cdef-123456789abc")
                UUID createdBy,

                @Schema(description = "Soft delete flag", example = "false")
                boolean deleted,

                @Schema(description = "Record creation time", example = "2026-04-05T10:15:30")
                LocalDateTime createdAt,

                @Schema(description = "Last updated time", example = "2026-04-05T10:20:30")
                LocalDateTime updatedAt
        ) { }

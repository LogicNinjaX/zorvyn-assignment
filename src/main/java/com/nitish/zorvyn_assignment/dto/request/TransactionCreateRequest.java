package com.nitish.zorvyn_assignment.dto.request;

import com.nitish.zorvyn_assignment.enums.Category;
import com.nitish.zorvyn_assignment.enums.RecordType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Request payload for creating a transaction")
public record TransactionCreateRequest
        (
                @Schema(
                        description = "Transaction amount",
                        example = "1500.50"
                )
                @NotNull(message = "Amount is required")
                @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
                BigDecimal amount,

                @Schema(
                        description = "Type of transaction",
                        example = "EXPENSE"
                )
                @NotNull(message = "Transaction type is required")
                RecordType type,

                @Schema(
                        description = "Transaction category",
                        example = "FOOD"
                )
                Category category,

                @Schema(
                        description = "Transaction timestamp",
                        example = "2026-04-05T10:15:30"
                )
                @NotNull(message = "Timestamp is required")
                LocalDateTime timeStamp,

                @Schema(
                        description = "Optional description of the transaction",
                        example = "Lunch at restaurant"
                )
                @Size(max = 255, message = "Description cannot exceed 255 characters")
                String description
        ) { }

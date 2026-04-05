package com.nitish.zorvyn_assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Monthly transaction summary")
public record MonthlySummaryResponse
        (
                @Schema(
                        description = "Month number (1 = January, 12 = December)",
                        example = "4"
                )

                Integer month,

                @Schema(
                        description = "Total amount for the month",
                        example = "15000.75"
                )
                BigDecimal total
        ) { }

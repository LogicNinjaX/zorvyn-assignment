package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Category-wise transaction summary")
public record CategorySummaryResponse
        (
                @Schema(
                        description = "Transaction category",
                        example = "FOOD"
                )
                Category category,

                @Schema(
                        description = "Total amount for this category",
                        example = "2500.75"
                )
                BigDecimal total
        ) { }

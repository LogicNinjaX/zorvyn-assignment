package com.nitish.zorvyn_assignment.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Dashboard summary response")
public record DashboardResponse
        (
                @Schema(description = "Total income amount", example = "50000")
                BigDecimal totalIncome,

                @Schema(description = "Total expense amount", example = "20000")
                BigDecimal totalExpense,

                @Schema(description = "Net balance (income - expense)", example = "30000")
                BigDecimal netBalance,

                @Schema(description = "Category-wise transaction summary")
                List<CategorySummaryResponse> categorySummary,

                @Schema(description = "Recent transactions list")
                List<TransactionDetailsResponse> recentTransactions,

                @Schema(description = "Monthly income summary")
                List<MonthlySummaryResponse> monthlyIncome
        ) { }

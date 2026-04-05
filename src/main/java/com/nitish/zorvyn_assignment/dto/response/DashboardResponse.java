package com.nitish.zorvyn_assignment.dto.response;


import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse
        (
                BigDecimal totalIncome,
                BigDecimal totalExpense,
                BigDecimal netBalance,
                List<CategorySummaryResponse> categorySummary,
                List<TransactionDetailsResponse> recentTransactions,
                List<MonthlySummaryResponse> monthlyIncome
        ) { }

package com.nitish.zorvyn_assignment.dto.response;

import com.nitish.zorvyn_assignment.dto.CategorySummaryDTO;
import com.nitish.zorvyn_assignment.dto.MonthlySummaryDTO;

import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse
        (
                BigDecimal totalIncome,
                BigDecimal totalExpense,
                BigDecimal netBalance,
                List<CategorySummaryDTO> categorySummary,
                List<TransactionDetailsResponse> recentTransactions,
                List<MonthlySummaryDTO> monthlyIncome
        ) { }

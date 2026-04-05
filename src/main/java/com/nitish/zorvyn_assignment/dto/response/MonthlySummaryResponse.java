package com.nitish.zorvyn_assignment.dto.response;

import java.math.BigDecimal;

public record MonthlySummaryResponse
        (
                Integer month,
                BigDecimal total
        ) { }

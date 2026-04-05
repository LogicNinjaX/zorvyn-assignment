package com.nitish.zorvyn_assignment.dto.response;

import java.math.BigDecimal;

public record CategorySummaryResponse
        (
                String category,
                BigDecimal total
        ) { }

package com.nitish.zorvyn_assignment.dto;

import com.nitish.zorvyn_assignment.enums.Category;

import java.math.BigDecimal;

public interface CategorySummaryDTO {
    Category getCategory();
    BigDecimal getTotal();
}

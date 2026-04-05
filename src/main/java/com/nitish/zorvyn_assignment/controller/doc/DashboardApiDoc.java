package com.nitish.zorvyn_assignment.controller.doc;


import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.DashboardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Dashboard APIs", description = "APIs for dashboard analytics and summaries")
@SecurityRequirement(name = "bearerAuth")
public interface DashboardApiDoc {

    @Operation(
            summary = "Get Dashboard Data",
            description = "Fetch summary data including total income, expenses, category-wise totals, recent transactions, and monthly trends"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Dashboard data fetched successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - insufficient role",
                    content = @Content
            )}
    )
    ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(HttpServletRequest servletRequest);
}

package com.nitish.zorvyn_assignment.controller;

import com.nitish.zorvyn_assignment.controller.doc.DashboardApiDoc;
import com.nitish.zorvyn_assignment.dto.response.ApiResponse;
import com.nitish.zorvyn_assignment.dto.response.DashboardResponse;
import com.nitish.zorvyn_assignment.service.DashboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/dashboard")
@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'VIEWER')")
public class DashboardController implements DashboardApiDoc {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(HttpServletRequest servletRequest){
        var response = dashboardService.getDashboard();

        return ResponseEntity.ok(ApiResponse.ok("Dashboard data fetched successfully", response, servletRequest));
    }
}

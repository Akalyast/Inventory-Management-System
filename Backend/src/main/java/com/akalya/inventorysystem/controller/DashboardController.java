package com.akalya.inventorysystem.controller;

import com.akalya.inventorysystem.dto.DashboardSummary;
import com.akalya.inventorysystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardSummary getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }
}

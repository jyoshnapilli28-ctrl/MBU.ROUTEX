package com.mbu.routex.management.controller;

import com.mbu.routex.management.service.ManagementReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementReportController {

    private final ManagementReportService managementReportService;

    @GetMapping("/reports")
    public String reportsView(
            @RequestParam(value = "reportType", required = false, defaultValue = "FLEET_UTILIZATION") String reportType,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        Map<String, Object> report = managementReportService.generateReport(reportType, startDate, endDate);
        model.addAttribute("report", report);
        model.addAttribute("selectedType", reportType);
        return "management/reports";
    }
}

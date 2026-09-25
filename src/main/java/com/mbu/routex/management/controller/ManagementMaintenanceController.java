package com.mbu.routex.management.controller;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.maintenance.entity.MaintenanceRecord;
import com.mbu.routex.management.service.ManagementMaintenanceService;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementMaintenanceController {

    private final ManagementMaintenanceService managementMaintenanceService;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    private static final List<String> STATUSES = Arrays.asList(
            "SCHEDULED", "IN_PROGRESS", "COMPLETED", "OVERDUE"
    );

    @GetMapping("/maintenance")
    public String maintenanceView(Model model) {
        List<MaintenanceRecord> records = managementMaintenanceService.getAllRecords();
        List<Bus> buses = busRepository.findAll();
        model.addAttribute("records", records);
        model.addAttribute("buses", buses);
        model.addAttribute("statuses", STATUSES);
        return "management/maintenance";
    }

    @PostMapping("/maintenance/add")
    public String addMaintenance(
            @RequestParam Long busId,
            @RequestParam String component,
            @RequestParam String description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate serviceDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nextServiceDate,
            @RequestParam String status,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        managementMaintenanceService.addRecord(user.getId(), busId, component, description, serviceDate, nextServiceDate, status);
        redirectAttributes.addFlashAttribute("success", "Maintenance record logged successfully.");
        return "redirect:/management/maintenance";
    }
}

package com.mbu.routex.management.controller;

import com.mbu.routex.management.dto.TripSummaryDto;
import com.mbu.routex.management.service.ManagementTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementTripController {

    private final ManagementTripService managementTripService;

    @GetMapping("/trips")
    public String trips(@RequestParam(value = "status", required = false) String status, Model model) {
        List<TripSummaryDto> trips = managementTripService.getAllTrips(status);
        model.addAttribute("trips", trips);
        model.addAttribute("currentStatus", status != null ? status : "ALL");
        return "management/trips";
    }
}

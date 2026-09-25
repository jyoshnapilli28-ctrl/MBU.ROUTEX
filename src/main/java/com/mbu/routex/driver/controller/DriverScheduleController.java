package com.mbu.routex.driver.controller;

import com.mbu.routex.driver.service.DriverScheduleService;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverScheduleController {

    private final DriverScheduleService driverScheduleService;
    private final UserRepository userRepository;

    @GetMapping("/schedules")
    public String schedules(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Trip> trips = driverScheduleService.getSchedulesForDriver(user.getId());
        model.addAttribute("trips", trips);
        return "driver/schedules";
    }
}

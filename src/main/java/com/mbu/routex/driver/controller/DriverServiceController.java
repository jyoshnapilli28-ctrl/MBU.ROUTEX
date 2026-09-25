package com.mbu.routex.driver.controller;

import com.mbu.routex.driver.dto.DriverServiceDto;
import com.mbu.routex.driver.service.DriverServiceDetailsService;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverServiceController {

    private final DriverServiceDetailsService driverServiceDetailsService;
    private final UserRepository userRepository;

    @GetMapping("/service-details")
    public String serviceDetails(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        DriverServiceDto serviceDetails = driverServiceDetailsService.getServiceDetails(user.getId());
        model.addAttribute("serviceDetails", serviceDetails);
        return "driver/service-details";
    }
}

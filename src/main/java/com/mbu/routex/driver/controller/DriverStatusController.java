package com.mbu.routex.driver.controller;

import com.mbu.routex.driver.dto.DriverStatusDto;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.driver.service.DriverStatusService;
import com.mbu.routex.notification.entity.Notification;
import com.mbu.routex.notification.repository.NotificationRepository;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverStatusController {

    private final DriverStatusService driverStatusService;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final NotificationRepository notificationRepository;

    private static final List<String> BUS_STATUSES = Arrays.asList(
            "ACTIVE", "ON_ROUTE", "DELAYED", "STOPPED", "MAINTENANCE", "COMPLETED"
    );

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("greeting", "Hello Drivers !!!");
        return "driver/dashboard";
    }

    @GetMapping("/bus-status")
    public String busStatusView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        DriverStatusDto busStatus = driverStatusService.getDriverBusStatus(user.getId());
        model.addAttribute("busStatus", busStatus);
        model.addAttribute("statuses", BUS_STATUSES);
        return "driver/bus-status";
    }

    @PostMapping("/bus-status/update")
    public String updateBusStatus(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String status,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        driverStatusService.updateBusStatus(user.getId(), status);
        redirectAttributes.addFlashAttribute("success", "Bus status updated to " + status + " successfully.");
        return "redirect:/driver/bus-status";
    }

    @GetMapping("/notifications")
    public String notifications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        model.addAttribute("notifications", notifications);
        model.addAttribute("role", "driver");
        return "driver/notifications";
    }

    @PostMapping("/notifications/{id}/read")
    public String markNotificationRead(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setIsRead(true);
            notificationRepository.save(n);
        });
        redirectAttributes.addFlashAttribute("success", "Notification marked as read");
        return "redirect:/driver/notifications";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Driver driver = driverRepository.findByUserId(user.getId()).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("driver", driver);
        model.addAttribute("role", "driver");
        return "driver/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String email,
            @RequestParam(required = false) String phone,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        user.setEmail(email);
        userRepository.save(user);

        Driver driver = driverRepository.findByUserId(user.getId()).orElse(null);
        if (driver != null && phone != null) {
            driver.setPhone(phone);
            driverRepository.save(driver);
        }

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        return "redirect:/driver/profile";
    }

    @GetMapping("/track-updates")
    public String trackUpdates() {
        return "redirect:/driver/bus-status";
    }
}

package com.mbu.routex.management.controller;

import com.mbu.routex.management.dto.BusDetailDto;
import com.mbu.routex.management.entity.ManagementUser;
import com.mbu.routex.management.repository.ManagementUserRepository;
import com.mbu.routex.management.service.ManagementBusService;
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

import java.util.List;

@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementBusController {

    private final ManagementBusService managementBusService;
    private final UserRepository userRepository;
    private final ManagementUserRepository managementUserRepository;
    private final NotificationRepository notificationRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("greeting", "Welcome Management !!!");
        return "management/dashboard";
    }

    @GetMapping("/bus-details")
    public String busDetails(@RequestParam(value = "status", required = false) String status, Model model) {
        List<BusDetailDto> buses = managementBusService.getAllBuses(status);
        model.addAttribute("buses", buses);
        model.addAttribute("currentStatus", status != null ? status : "ALL");
        return "management/bus-details";
    }

    @GetMapping("/notifications")
    public String notifications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        model.addAttribute("notifications", notifications);
        model.addAttribute("role", "management");
        return "management/notifications";
    }

    @PostMapping("/notifications/{id}/read")
    public String markNotificationRead(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setIsRead(true);
            notificationRepository.save(n);
        });
        redirectAttributes.addFlashAttribute("success", "Notification marked as read");
        return "redirect:/management/notifications";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        ManagementUser mgmtUser = managementUserRepository.findByUserId(user.getId()).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("mgmtUser", mgmtUser);
        model.addAttribute("role", "management");
        return "management/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        user.setEmail(email);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        return "redirect:/management/profile";
    }

    @GetMapping("/track-updates")
    public String trackUpdates() {
        return "redirect:/management/trips";
    }
}

package com.mbu.routex.student.controller;

import com.mbu.routex.notification.entity.Notification;
import com.mbu.routex.notification.repository.NotificationRepository;
import com.mbu.routex.student.dto.StudentBusDto;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.repository.StudentRepository;
import com.mbu.routex.student.service.StudentBusService;
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
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentBusController {

    private final StudentBusService studentBusService;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final NotificationRepository notificationRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("greeting", "Hello MBUIans !!!");
        return "student/dashboard";
    }

    @GetMapping("/my-bus")
    public String myBus(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        StudentBusDto busInfo = studentBusService.getMyBus(user.getId());
        model.addAttribute("busInfo", busInfo);
        return "student/my-bus";
    }

    @GetMapping("/notifications")
    public String notifications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        model.addAttribute("notifications", notifications);
        model.addAttribute("role", "student");
        return "student/notifications";
    }

    @PostMapping("/notifications/{id}/read")
    public String markNotificationRead(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setIsRead(true);
            notificationRepository.save(n);
        });
        redirectAttributes.addFlashAttribute("success", "Notification marked as read");
        return "redirect:/student/notifications";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Student student = studentRepository.findByUserId(user.getId()).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("student", student);
        model.addAttribute("role", "student");
        return "student/profile";
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

        Student student = studentRepository.findByUserId(user.getId()).orElse(null);
        if (student != null && phone != null) {
            student.setPhone(phone);
            studentRepository.save(student);
        }

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        return "redirect:/student/profile";
    }

    @GetMapping("/track-updates")
    public String trackUpdates() {
        return "redirect:/student/live-track";
    }
}

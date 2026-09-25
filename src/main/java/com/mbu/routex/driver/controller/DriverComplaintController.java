package com.mbu.routex.driver.controller;

import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.driver.service.DriverComplaintService;
import com.mbu.routex.student.dto.ComplaintRequestDto;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverComplaintController {

    private final DriverComplaintService driverComplaintService;
    private final UserRepository userRepository;

    private static final List<String> CATEGORIES = Arrays.asList(
            "Tyre problem",
            "Brake problem",
            "Engine problem",
            "Mechanical issue",
            "Cleanliness",
            "Bus damage",
            "Other"
    );

    @GetMapping("/bus-complaints")
    public String busComplaintsView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Complaint> complaints = driverComplaintService.getComplaintsForDriver(user.getId());
        model.addAttribute("complaints", complaints);
        model.addAttribute("categories", CATEGORIES);
        if (!model.containsAttribute("complaintRequest")) {
            model.addAttribute("complaintRequest", new ComplaintRequestDto());
        }
        return "driver/bus-complaints";
    }

    @PostMapping("/bus-complaints")
    public String submitBusComplaint(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute ComplaintRequestDto complaintRequest,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        driverComplaintService.submitComplaint(user.getId(), complaintRequest);
        redirectAttributes.addFlashAttribute("success", "Vehicle maintenance issue logged successfully.");
        return "redirect:/driver/bus-complaints";
    }
}

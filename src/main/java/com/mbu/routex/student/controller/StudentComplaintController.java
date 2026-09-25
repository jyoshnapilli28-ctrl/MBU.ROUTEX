package com.mbu.routex.student.controller;

import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.student.dto.ComplaintRequestDto;
import com.mbu.routex.student.service.StudentComplaintService;
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
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentComplaintController {

    private final StudentComplaintService studentComplaintService;
    private final UserRepository userRepository;

    private static final List<String> CATEGORIES = Arrays.asList(
            "Delay",
            "Driver behaviour",
            "Bus condition",
            "Overcrowding",
            "Route issue",
            "Cleanliness",
            "Other"
    );

    @GetMapping("/complaint")
    public String complaintView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Complaint> complaints = studentComplaintService.getComplaintsForStudent(user.getId());
        model.addAttribute("complaints", complaints);
        model.addAttribute("categories", CATEGORIES);
        if (!model.containsAttribute("complaintRequest")) {
            model.addAttribute("complaintRequest", new ComplaintRequestDto());
        }
        return "student/complaint";
    }

    @PostMapping("/complaint")
    public String submitComplaint(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute ComplaintRequestDto complaintRequest,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        studentComplaintService.submitComplaint(user.getId(), complaintRequest);
        redirectAttributes.addFlashAttribute("success", "Your complaint has been submitted successfully and is under review.");
        return "redirect:/student/complaint";
    }
}

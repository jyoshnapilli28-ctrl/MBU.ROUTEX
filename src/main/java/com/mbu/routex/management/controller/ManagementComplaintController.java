package com.mbu.routex.management.controller;

import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.management.service.ManagementComplaintService;
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
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementComplaintController {

    private final ManagementComplaintService managementComplaintService;
    private final UserRepository userRepository;

    private static final List<String> COMPLAINT_STATUSES = Arrays.asList(
            "OPEN", "IN_REVIEW", "RESOLVED", "ESCALATED"
    );

    @GetMapping("/complaints")
    public String complaintsView(@RequestParam(value = "status", required = false) String status, Model model) {
        List<Complaint> complaints = managementComplaintService.getAllComplaints(status);
        model.addAttribute("complaints", complaints);
        model.addAttribute("statuses", COMPLAINT_STATUSES);
        model.addAttribute("currentStatus", status != null ? status : "ALL");
        return "management/complaints";
    }

    @PostMapping("/complaints/{id}/update")
    public String updateComplaint(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String response,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        managementComplaintService.updateComplaint(id, user.getId(), status, response);
        redirectAttributes.addFlashAttribute("success", "Complaint #" + id + " updated to " + status + ".");
        return "redirect:/management/complaints";
    }
}

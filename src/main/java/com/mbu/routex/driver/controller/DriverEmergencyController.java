package com.mbu.routex.driver.controller;

import com.mbu.routex.emergency.entity.EmergencyContact;
import com.mbu.routex.emergency.repository.EmergencyContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverEmergencyController {

    private final EmergencyContactRepository emergencyContactRepository;

    @GetMapping("/emergency")
    public String emergency(Model model) {
        List<EmergencyContact> contacts = emergencyContactRepository.findByIsActiveTrueOrderByCategoryAsc();
        model.addAttribute("contacts", contacts);
        return "driver/emergency";
    }
}

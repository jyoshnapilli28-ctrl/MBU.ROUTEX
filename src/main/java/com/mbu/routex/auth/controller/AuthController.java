package com.mbu.routex.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String roleSelect(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password. Please select your role and try again.");
        }
        return "login/role-select";
    }

    @GetMapping("/login/student")
    public String studentLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid student username or password.");
        }
        return "login/student-login";
    }

    @GetMapping("/login/driver")
    public String driverLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid driver username or password.");
        }
        return "login/driver-login";
    }

    @GetMapping("/login/management")
    public String managementLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid management username or password.");
        }
        return "login/management-login";
    }
}

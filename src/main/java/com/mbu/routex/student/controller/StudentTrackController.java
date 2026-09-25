package com.mbu.routex.student.controller;

import com.mbu.routex.student.dto.StudentTrackDto;
import com.mbu.routex.student.service.StudentTrackService;
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
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentTrackController {

    private final StudentTrackService studentTrackService;
    private final UserRepository userRepository;

    @GetMapping("/live-track")
    public String liveTrack(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        StudentTrackDto trackInfo = studentTrackService.getLiveTrack(user.getId());
        model.addAttribute("trackInfo", trackInfo);
        return "student/live-track";
    }
}

package com.mbu.routex.student.controller;

import com.mbu.routex.attendance.entity.Attendance;
import com.mbu.routex.attendance.entity.QRCode;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.service.StudentAttendanceService;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;
    private final UserRepository userRepository;

    @GetMapping("/attendance")
    public String attendanceView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Student student = studentAttendanceService.getStudent(user.getId());
        QRCode qrCode = studentAttendanceService.getOrCreateQrCode(user.getId());
        List<Attendance> history = studentAttendanceService.getAttendanceHistory(user.getId());

        model.addAttribute("student", student);
        model.addAttribute("qrCode", qrCode);
        model.addAttribute("history", history);
        return "student/attendance";
    }

    @GetMapping(value = "/attendance/qr-image", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getQrCodeImage(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        return studentAttendanceService.getQrCodeImage(user.getId());
    }
}

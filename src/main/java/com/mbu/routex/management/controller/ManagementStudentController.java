package com.mbu.routex.management.controller;

import com.mbu.routex.management.service.ManagementStudentService;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementStudentController {

    private final ManagementStudentService managementStudentService;

    @GetMapping("/students")
    public String studentsView(Model model) {
        List<Student> students = managementStudentService.getAllStudents();
        List<StudentBusAssignment> assignments = managementStudentService.getAllAssignments();
        Map<String, List<Student>> grouped = managementStudentService.getStudentsGroupedByBus();

        model.addAttribute("students", students);
        model.addAttribute("assignments", assignments);
        model.addAttribute("grouped", grouped);
        return "management/students";
    }
}

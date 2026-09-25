package com.mbu.routex.management.service;

import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagementStudentService {

    private final StudentRepository studentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentBusAssignment> getAllAssignments() {
        return studentBusAssignmentRepository.findAll();
    }

    public Map<String, List<Student>> getStudentsGroupedByBus() {
        Map<String, List<Student>> map = new LinkedHashMap<>();
        List<StudentBusAssignment> assignments = studentBusAssignmentRepository.findAll();
        for (StudentBusAssignment a : assignments) {
            String busKey = a.getBus() != null ? a.getBus().getBusNumber() : "Unassigned";
            map.computeIfAbsent(busKey, k -> new ArrayList<>()).add(a.getStudent());
        }
        return map;
    }
}

package com.mbu.routex.student.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.complaint.entity.ComplaintStatus;
import com.mbu.routex.complaint.entity.ComplaintStatusEnum;
import com.mbu.routex.complaint.entity.SubmitterType;
import com.mbu.routex.complaint.repository.ComplaintRepository;
import com.mbu.routex.complaint.repository.ComplaintStatusRepository;
import com.mbu.routex.student.dto.ComplaintRequestDto;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintStatusRepository complaintStatusRepository;
    private final StudentRepository studentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final BusRepository busRepository;

    @Transactional(readOnly = true)
    public List<Complaint> getComplaintsForStudent(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));
        return complaintRepository.findBySubmittedByAndSubmitterTypeOrderByCreatedAtDesc(student.getId(), SubmitterType.STUDENT);
    }

    @Transactional
    public void submitComplaint(Long userId, ComplaintRequestDto request) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));

        Bus assignedBus = null;
        if (request.getBusNumber() != null && !request.getBusNumber().isBlank()) {
            assignedBus = busRepository.findByBusNumber(request.getBusNumber()).orElse(null);
        }
        if (assignedBus == null) {
            Optional<StudentBusAssignment> assignment = studentBusAssignmentRepository.findByStudentIdAndIsActiveTrue(student.getId());
            if (assignment.isPresent()) {
                assignedBus = assignment.get().getBus();
            }
        }

        Complaint complaint = Complaint.builder()
                .submittedBy(student.getId())
                .submitterType(SubmitterType.STUDENT)
                .category(request.getCategory())
                .description(request.getDescription())
                .bus(assignedBus)
                .currentStatus(ComplaintStatusEnum.OPEN)
                .build();

        Complaint saved = complaintRepository.save(complaint);

        ComplaintStatus statusHistory = ComplaintStatus.builder()
                .complaint(saved)
                .status(ComplaintStatusEnum.OPEN)
                .changedBy(userId)
                .notes("Complaint submitted by student " + student.getFullName())
                .build();

        complaintStatusRepository.save(statusHistory);
    }
}

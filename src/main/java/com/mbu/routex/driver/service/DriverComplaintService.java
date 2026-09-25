package com.mbu.routex.driver.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.complaint.entity.ComplaintStatus;
import com.mbu.routex.complaint.entity.ComplaintStatusEnum;
import com.mbu.routex.complaint.entity.SubmitterType;
import com.mbu.routex.complaint.repository.ComplaintRepository;
import com.mbu.routex.complaint.repository.ComplaintStatusRepository;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.student.dto.ComplaintRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintStatusRepository complaintStatusRepository;
    private final DriverRepository driverRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;

    @Transactional(readOnly = true)
    public List<Complaint> getComplaintsForDriver(Long userId) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));
        return complaintRepository.findBySubmittedByAndSubmitterTypeOrderByCreatedAtDesc(driver.getId(), SubmitterType.DRIVER);
    }

    @Transactional
    public void submitComplaint(Long userId, ComplaintRequestDto request) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));

        Bus assignedBus = null;
        Optional<DriverAssignment> assignment = driverAssignmentRepository.findByDriverIdAndIsActiveTrue(driver.getId());
        if (assignment.isPresent()) {
            assignedBus = assignment.get().getBus();
        }

        Complaint complaint = Complaint.builder()
                .submittedBy(driver.getId())
                .submitterType(SubmitterType.DRIVER)
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
                .notes("Bus defect report submitted by driver " + driver.getFullName())
                .build();

        complaintStatusRepository.save(statusHistory);
    }
}

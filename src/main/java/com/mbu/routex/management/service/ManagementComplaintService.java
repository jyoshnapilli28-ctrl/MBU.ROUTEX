package com.mbu.routex.management.service;

import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.complaint.entity.ComplaintStatus;
import com.mbu.routex.complaint.entity.ComplaintStatusEnum;
import com.mbu.routex.complaint.repository.ComplaintRepository;
import com.mbu.routex.complaint.repository.ComplaintStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagementComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintStatusRepository complaintStatusRepository;

    @Transactional(readOnly = true)
    public List<Complaint> getAllComplaints(String statusFilter) {
        if (statusFilter != null && !statusFilter.isBlank() && !statusFilter.equalsIgnoreCase("ALL")) {
            try {
                ComplaintStatusEnum status = ComplaintStatusEnum.valueOf(statusFilter.toUpperCase());
                return complaintRepository.findByCurrentStatusOrderByCreatedAtDesc(status);
            } catch (IllegalArgumentException ignored) {
            }
        }
        return complaintRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint #" + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<ComplaintStatus> getStatusHistory(Long complaintId) {
        return complaintStatusRepository.findByComplaintIdOrderByChangedAtAsc(complaintId);
    }

    @Transactional
    public void updateComplaint(Long id, Long managementUserId, String statusStr, String responseText) {
        Complaint complaint = getComplaintById(id);

        ComplaintStatusEnum newStatus;
        try {
            newStatus = ComplaintStatusEnum.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            newStatus = complaint.getCurrentStatus();
        }

        complaint.setCurrentStatus(newStatus);
        if (responseText != null && !responseText.isBlank()) {
            complaint.setManagementResponse(responseText);
        }
        complaintRepository.save(complaint);

        ComplaintStatus history = ComplaintStatus.builder()
                .complaint(complaint)
                .status(newStatus)
                .changedBy(managementUserId)
                .notes(responseText != null && !responseText.isBlank() ? responseText : "Status changed to " + newStatus)
                .build();
        complaintStatusRepository.save(history);
    }
}

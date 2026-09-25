package com.mbu.routex.complaint.repository;

import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.complaint.entity.ComplaintStatusEnum;
import com.mbu.routex.complaint.entity.SubmitterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findBySubmittedByAndSubmitterTypeOrderByCreatedAtDesc(Long submittedBy, SubmitterType type);
    List<Complaint> findAllByOrderByCreatedAtDesc();
    List<Complaint> findByCurrentStatusOrderByCreatedAtDesc(ComplaintStatusEnum status);
    List<Complaint> findBySubmitterTypeOrderByCreatedAtDesc(SubmitterType type);
}

package com.mbu.routex.complaint.repository;

import com.mbu.routex.complaint.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
    List<ComplaintStatus> findByComplaintIdOrderByChangedAtAsc(Long complaintId);
}

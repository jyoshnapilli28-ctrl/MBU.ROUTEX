package com.mbu.routex.management.repository;

import com.mbu.routex.management.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByGeneratedByIdOrderByGeneratedAtDesc(Long managementUserId);
    List<Report> findAllByOrderByGeneratedAtDesc();
}

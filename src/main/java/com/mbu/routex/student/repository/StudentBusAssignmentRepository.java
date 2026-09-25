package com.mbu.routex.student.repository;

import com.mbu.routex.student.entity.StudentBusAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface StudentBusAssignmentRepository extends JpaRepository<StudentBusAssignment, Long> {
    Optional<StudentBusAssignment> findByStudentIdAndIsActiveTrue(Long studentId);
    List<StudentBusAssignment> findByBusIdAndIsActiveTrue(Long busId);
    long countByBusIdAndIsActiveTrue(Long busId);
}

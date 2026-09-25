package com.mbu.routex.bus.repository;

import com.mbu.routex.bus.entity.BusAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BusAssignmentRepository extends JpaRepository<BusAssignment, Long> {
    Optional<BusAssignment> findByBusIdAndIsActiveTrue(Long busId);
}

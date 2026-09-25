package com.mbu.routex.driver.repository;

import com.mbu.routex.driver.entity.DriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DriverAssignmentRepository extends JpaRepository<DriverAssignment, Long> {
    Optional<DriverAssignment> findByDriverIdAndIsActiveTrue(Long driverId);
    Optional<DriverAssignment> findByBusIdAndIsActiveTrue(Long busId);
}

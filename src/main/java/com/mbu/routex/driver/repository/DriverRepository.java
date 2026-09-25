package com.mbu.routex.driver.repository;

import com.mbu.routex.driver.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUserId(Long userId);
    Optional<Driver> findByDriverId(String driverId);
}

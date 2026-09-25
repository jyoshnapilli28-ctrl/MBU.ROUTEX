package com.mbu.routex.maintenance.repository;

import com.mbu.routex.maintenance.entity.MaintenanceRecord;
import com.mbu.routex.maintenance.entity.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {
    List<MaintenanceRecord> findByBusIdOrderByServiceDateDesc(Long busId);
    List<MaintenanceRecord> findAllByOrderByServiceDateDesc();
    List<MaintenanceRecord> findByStatus(MaintenanceStatus status);
}

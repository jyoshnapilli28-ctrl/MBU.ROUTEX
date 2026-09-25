package com.mbu.routex.management.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.maintenance.entity.MaintenanceRecord;
import com.mbu.routex.maintenance.entity.MaintenanceStatus;
import com.mbu.routex.maintenance.repository.MaintenanceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagementMaintenanceService {

    private final MaintenanceRecordRepository maintenanceRecordRepository;
    private final BusRepository busRepository;

    @Transactional(readOnly = true)
    public List<MaintenanceRecord> getAllRecords() {
        return maintenanceRecordRepository.findAllByOrderByServiceDateDesc();
    }

    @Transactional
    public void addRecord(Long managementUserId, Long busId, String component, String description,
                          LocalDate serviceDate, LocalDate nextServiceDate, String statusStr) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus #" + busId + " not found"));

        MaintenanceStatus status = MaintenanceStatus.SCHEDULED;
        try {
            status = MaintenanceStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException ignored) {
        }

        MaintenanceRecord record = MaintenanceRecord.builder()
                .bus(bus)
                .component(component)
                .description(description)
                .serviceDate(serviceDate != null ? serviceDate : LocalDate.now())
                .nextServiceDate(nextServiceDate)
                .status(status)
                .recordedBy(managementUserId)
                .build();

        maintenanceRecordRepository.save(record);
    }
}

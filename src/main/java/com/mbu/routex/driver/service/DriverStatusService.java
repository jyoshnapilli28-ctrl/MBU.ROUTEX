package com.mbu.routex.driver.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.entity.BusStatus;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.common.util.DateUtil;
import com.mbu.routex.driver.dto.DriverStatusDto;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverStatusService {

    private final DriverRepository driverRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final BusRepository busRepository;

    @Transactional(readOnly = true)
    public DriverStatusDto getDriverBusStatus(Long userId) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));

        Optional<DriverAssignment> driverAssignmentOpt = driverAssignmentRepository.findByDriverIdAndIsActiveTrue(driver.getId());
        if (driverAssignmentOpt.isEmpty()) {
            return DriverStatusDto.builder()
                    .busNumber("Unassigned")
                    .registration("-")
                    .currentStatus("INACTIVE")
                    .routeCode("-")
                    .routeName("No Route Assigned")
                    .lastUpdated(DateUtil.formatTime(LocalDateTime.now()))
                    .build();
        }

        Bus bus = driverAssignmentOpt.get().getBus();
        String routeCode = "RTE-001";
        String routeName = "Standard Route";

        Optional<BusAssignment> busAssignmentOpt = busAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (busAssignmentOpt.isPresent()) {
            routeCode = busAssignmentOpt.get().getRoute().getRouteCode();
            routeName = busAssignmentOpt.get().getRoute().getRouteName();
        }

        return DriverStatusDto.builder()
                .busId(bus.getId())
                .busNumber(bus.getBusNumber())
                .registration(bus.getRegistration())
                .currentStatus(bus.getStatus() != null ? bus.getStatus().name() : "ACTIVE")
                .routeCode(routeCode)
                .routeName(routeName)
                .lastUpdated(bus.getUpdatedAt() != null ? DateUtil.formatTime(bus.getUpdatedAt()) : DateUtil.formatTime(LocalDateTime.now()))
                .build();
    }

    @Transactional
    public void updateBusStatus(Long userId, String statusStr) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));

        DriverAssignment driverAssignment = driverAssignmentRepository.findByDriverIdAndIsActiveTrue(driver.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No active bus assignment found for driver: " + driver.getFullName()));

        Bus bus = driverAssignment.getBus();
        try {
            BusStatus status = BusStatus.valueOf(statusStr.toUpperCase());
            bus.setStatus(status);
            busRepository.save(bus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid bus status: " + statusStr);
        }
    }
}

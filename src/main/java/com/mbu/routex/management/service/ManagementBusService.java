package com.mbu.routex.management.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.entity.BusStatus;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.management.dto.BusDetailDto;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagementBusService {

    private final BusRepository busRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;

    public List<BusDetailDto> getAllBuses(String statusFilter) {
        List<Bus> buses;
        if (statusFilter != null && !statusFilter.isBlank() && !statusFilter.equalsIgnoreCase("ALL")) {
            try {
                BusStatus status = BusStatus.valueOf(statusFilter.toUpperCase());
                buses = busRepository.findByStatus(status);
            } catch (IllegalArgumentException e) {
                buses = busRepository.findAll();
            }
        } else {
            buses = busRepository.findAll();
        }

        List<BusDetailDto> result = new ArrayList<>();
        for (Bus bus : buses) {
            String routeName = "Unassigned";
            String routeCode = "-";
            Optional<BusAssignment> busAssignmentOpt = busAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
            if (busAssignmentOpt.isPresent()) {
                routeName = busAssignmentOpt.get().getRoute().getRouteName();
                routeCode = busAssignmentOpt.get().getRoute().getRouteCode();
            }

            String driverName = "Unassigned";
            String driverPhone = "-";
            Optional<DriverAssignment> driverAssignmentOpt = driverAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
            if (driverAssignmentOpt.isPresent()) {
                Driver driver = driverAssignmentOpt.get().getDriver();
                driverName = driver.getFullName();
                driverPhone = driver.getPhone();
            }

            long studentCount = studentBusAssignmentRepository.countByBusIdAndIsActiveTrue(bus.getId());

            result.add(BusDetailDto.builder()
                    .id(bus.getId())
                    .busNumber(bus.getBusNumber())
                    .registration(bus.getRegistration())
                    .capacity(bus.getCapacity())
                    .status(bus.getStatus() != null ? bus.getStatus().name() : "ACTIVE")
                    .routeName(routeName)
                    .routeCode(routeCode)
                    .driverName(driverName)
                    .driverPhone(driverPhone)
                    .studentCount(studentCount)
                    .build());
        }
        return result;
    }
}

package com.mbu.routex.student.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.route.entity.Route;
import com.mbu.routex.route.entity.RouteStop;
import com.mbu.routex.route.repository.RouteStopRepository;
import com.mbu.routex.student.dto.StudentBusDto;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentBusService {

    private final StudentRepository studentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final RouteStopRepository routeStopRepository;

    public StudentBusDto getMyBus(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found for user ID: " + userId));

        Optional<StudentBusAssignment> assignmentOpt = studentBusAssignmentRepository.findByStudentIdAndIsActiveTrue(student.getId());
        if (assignmentOpt.isEmpty()) {
            return StudentBusDto.builder()
                    .busNumber("Not Assigned")
                    .routeName("No Active Route")
                    .driverName("Unassigned")
                    .driverPhone("-")
                    .busStatus("INACTIVE")
                    .pickupStop("Not specified")
                    .stops(Collections.emptyList())
                    .build();
        }

        StudentBusAssignment assignment = assignmentOpt.get();
        Bus bus = assignment.getBus();

        String routeName = "Standard Route";
        String routeCode = "-";
        List<String> stops = Collections.emptyList();

        Optional<BusAssignment> busAssignmentOpt = busAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (busAssignmentOpt.isPresent()) {
            Route route = busAssignmentOpt.get().getRoute();
            routeName = route.getRouteName();
            routeCode = route.getRouteCode();
            List<RouteStop> routeStops = routeStopRepository.findByRouteIdOrderByStopOrderAsc(route.getId());
            stops = routeStops.stream()
                    .map(RouteStop::getStopName)
                    .collect(Collectors.toList());
        }

        String driverName = "Assigned Driver";
        String driverPhone = "-";
        Optional<DriverAssignment> driverAssignmentOpt = driverAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (driverAssignmentOpt.isPresent()) {
            Driver driver = driverAssignmentOpt.get().getDriver();
            driverName = driver.getFullName();
            driverPhone = driver.getPhone();
        }

        return StudentBusDto.builder()
                .busNumber(bus.getBusNumber())
                .registration(bus.getRegistration())
                .capacity(bus.getCapacity())
                .routeName(routeName)
                .routeCode(routeCode)
                .driverName(driverName)
                .driverPhone(driverPhone)
                .busStatus(bus.getStatus() != null ? bus.getStatus().name() : "ACTIVE")
                .pickupStop(assignment.getBoardingStop() != null ? assignment.getBoardingStop().getStopName() : "Campus Main Gate")
                .stops(stops)
                .build();
    }
}

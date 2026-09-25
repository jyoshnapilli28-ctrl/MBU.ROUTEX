package com.mbu.routex.driver.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.common.util.DateUtil;
import com.mbu.routex.driver.dto.DriverServiceDto;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.route.entity.Route;
import com.mbu.routex.route.entity.RouteStop;
import com.mbu.routex.route.repository.RouteStopRepository;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.entity.TripStatus;
import com.mbu.routex.trip.repository.TripRepository;
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
public class DriverServiceDetailsService {

    private final DriverRepository driverRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final RouteStopRepository routeStopRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final TripRepository tripRepository;

    public DriverServiceDto getServiceDetails(Long userId) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));

        Optional<DriverAssignment> driverAssignmentOpt = driverAssignmentRepository.findByDriverIdAndIsActiveTrue(driver.getId());
        if (driverAssignmentOpt.isEmpty()) {
            return DriverServiceDto.builder()
                    .driverName(driver.getFullName())
                    .licenseNumber(driver.getLicenseNumber())
                    .driverPhone(driver.getPhone())
                    .busNumber("Unassigned")
                    .registration("-")
                    .busCapacity(0)
                    .studentCount(0L)
                    .routeName("No Route Assigned")
                    .routeCode("-")
                    .stops(Collections.emptyList())
                    .tripStatus("SCHEDULED")
                    .departureTime("08:00 AM")
                    .build();
        }

        Bus bus = driverAssignmentOpt.get().getBus();
        long studentCount = studentBusAssignmentRepository.countByBusIdAndIsActiveTrue(bus.getId());

        String routeName = "Campus Route";
        String routeCode = "RTE-001";
        List<String> stops = Collections.emptyList();

        Optional<BusAssignment> busAssignmentOpt = busAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (busAssignmentOpt.isPresent()) {
            Route route = busAssignmentOpt.get().getRoute();
            routeName = route.getRouteName();
            routeCode = route.getRouteCode();
            List<RouteStop> routeStops = routeStopRepository.findByRouteIdOrderByStopOrderAsc(route.getId());
            stops = routeStops.stream().map(RouteStop::getStopName).collect(Collectors.toList());
        }

        String tripStatus = "SCHEDULED";
        String departureTime = "07:30 AM";
        Optional<Trip> activeTripOpt = tripRepository.findFirstByBusIdAndStatusOrderByScheduledDepartureDesc(bus.getId(), TripStatus.ACTIVE);
        if (activeTripOpt.isEmpty()) {
            activeTripOpt = tripRepository.findFirstByBusIdAndStatusOrderByScheduledDepartureDesc(bus.getId(), TripStatus.ON_ROUTE);
        }
        if (activeTripOpt.isPresent()) {
            tripStatus = activeTripOpt.get().getStatus().name();
            if (activeTripOpt.get().getScheduledDeparture() != null) {
                departureTime = DateUtil.formatTime(activeTripOpt.get().getScheduledDeparture());
            }
        }

        return DriverServiceDto.builder()
                .driverName(driver.getFullName())
                .licenseNumber(driver.getLicenseNumber())
                .driverPhone(driver.getPhone())
                .busNumber(bus.getBusNumber())
                .registration(bus.getRegistration())
                .busCapacity(bus.getCapacity())
                .studentCount(studentCount)
                .routeName(routeName)
                .routeCode(routeCode)
                .stops(stops)
                .tripStatus(tripStatus)
                .departureTime(departureTime)
                .build();
    }
}

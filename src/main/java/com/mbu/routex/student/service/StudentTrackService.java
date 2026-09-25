package com.mbu.routex.student.service;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.common.util.DateUtil;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.route.entity.Route;
import com.mbu.routex.route.entity.RouteStop;
import com.mbu.routex.route.repository.RouteStopRepository;
import com.mbu.routex.student.dto.StudentTrackDto;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.student.repository.StudentRepository;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.entity.TripStatus;
import com.mbu.routex.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentTrackService {

    private final StudentRepository studentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final RouteStopRepository routeStopRepository;
    private final TripRepository tripRepository;

    public StudentTrackDto getLiveTrack(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));

        Optional<StudentBusAssignment> assignmentOpt = studentBusAssignmentRepository.findByStudentIdAndIsActiveTrue(student.getId());
        if (assignmentOpt.isEmpty()) {
            return StudentTrackDto.builder()
                    .busNumber("Not Assigned")
                    .routeName("No Assigned Route")
                    .routeCode("-")
                    .tripStatus("SCHEDULED")
                    .currentStop("Campus Hub")
                    .nextStop("Main Gate")
                    .etaMinutes(15)
                    .lastUpdated(DateUtil.formatTime(LocalDateTime.now()))
                    .departureTime("08:00 AM")
                    .driverName("Unassigned")
                    .build();
        }

        Bus bus = assignmentOpt.get().getBus();
        String routeName = "University Route";
        String routeCode = "RTE-001";
        String currentStop = "Main Gate Circle";
        String nextStop = "Engineering Block";

        Optional<BusAssignment> busAssignmentOpt = busAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (busAssignmentOpt.isPresent()) {
            Route route = busAssignmentOpt.get().getRoute();
            routeName = route.getRouteName();
            routeCode = route.getRouteCode();
            List<RouteStop> stops = routeStopRepository.findByRouteIdOrderByStopOrderAsc(route.getId());
            if (stops.size() >= 2) {
                currentStop = stops.get(0).getStopName();
                nextStop = stops.get(1).getStopName();
            } else if (!stops.isEmpty()) {
                currentStop = stops.get(0).getStopName();
                nextStop = stops.get(0).getStopName();
            }
        }

        String driverName = "Assigned Driver";
        Optional<DriverAssignment> driverAssignmentOpt = driverAssignmentRepository.findByBusIdAndIsActiveTrue(bus.getId());
        if (driverAssignmentOpt.isPresent()) {
            driverName = driverAssignmentOpt.get().getDriver().getFullName();
        }

        String tripStatus = "ON_ROUTE";
        String departureTime = "07:30 AM";
        int etaMinutes = 10;

        Optional<Trip> activeTripOpt = tripRepository.findFirstByBusIdAndStatusOrderByScheduledDepartureDesc(bus.getId(), TripStatus.ACTIVE);
        if (activeTripOpt.isEmpty()) {
            activeTripOpt = tripRepository.findFirstByBusIdAndStatusOrderByScheduledDepartureDesc(bus.getId(), TripStatus.ON_ROUTE);
        }

        if (activeTripOpt.isPresent()) {
            Trip trip = activeTripOpt.get();
            tripStatus = trip.getStatus() != null ? trip.getStatus().name() : "ON_ROUTE";
            if (trip.getScheduledDeparture() != null) {
                departureTime = DateUtil.formatTime(trip.getScheduledDeparture());
            }
            if (trip.getRoute() != null) {
                routeName = trip.getRoute().getRouteName();
                routeCode = trip.getRoute().getRouteCode();
            }
        }

        return StudentTrackDto.builder()
                .busNumber(bus.getBusNumber())
                .routeName(routeName)
                .routeCode(routeCode)
                .tripStatus(tripStatus)
                .currentStop(currentStop)
                .nextStop(nextStop)
                .etaMinutes(etaMinutes)
                .lastUpdated(DateUtil.formatTime(LocalDateTime.now()))
                .departureTime(departureTime)
                .driverName(driverName)
                .build();
    }
}

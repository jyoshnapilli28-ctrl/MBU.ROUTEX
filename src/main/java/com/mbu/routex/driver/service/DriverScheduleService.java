package com.mbu.routex.driver.service;

import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DriverScheduleService {

    private final DriverRepository driverRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final TripRepository tripRepository;

    public List<Trip> getSchedulesForDriver(Long userId) {
        Driver driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found for user ID: " + userId));

        List<Trip> trips = tripRepository.findByDriverIdOrderByScheduledDepartureDesc(driver.getId());
        if (trips.isEmpty()) {
            Optional<DriverAssignment> assignment = driverAssignmentRepository.findByDriverIdAndIsActiveTrue(driver.getId());
            if (assignment.isPresent()) {
                trips = tripRepository.findByBusId(assignment.get().getBus().getId());
            }
        }
        return trips;
    }
}

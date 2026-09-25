package com.mbu.routex.management.service;

import com.mbu.routex.common.util.DateUtil;
import com.mbu.routex.management.dto.TripSummaryDto;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.entity.TripStatus;
import com.mbu.routex.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagementTripService {

    private final TripRepository tripRepository;

    public List<TripSummaryDto> getAllTrips(String statusFilter) {
        List<Trip> trips;
        if (statusFilter != null && !statusFilter.isBlank() && !statusFilter.equalsIgnoreCase("ALL")) {
            try {
                TripStatus status = TripStatus.valueOf(statusFilter.toUpperCase());
                trips = tripRepository.findByStatus(status);
            } catch (IllegalArgumentException e) {
                trips = tripRepository.findAll();
            }
        } else {
            trips = tripRepository.findAll();
        }

        List<TripSummaryDto> result = new ArrayList<>();
        for (Trip t : trips) {
            result.add(TripSummaryDto.builder()
                    .id(t.getId())
                    .busNumber(t.getBus() != null ? t.getBus().getBusNumber() : "-")
                    .routeName(t.getRoute() != null ? t.getRoute().getRouteName() : "Standard Route")
                    .routeCode(t.getRoute() != null ? t.getRoute().getRouteCode() : "-")
                    .driverName(t.getDriver() != null ? t.getDriver().getFullName() : "Assigned Driver")
                    .scheduledDeparture(DateUtil.formatDateTime(t.getScheduledDeparture()))
                    .scheduledArrival(DateUtil.formatTime(t.getScheduledArrival()))
                    .status(t.getStatus() != null ? t.getStatus().name() : "SCHEDULED")
                    .build());
        }
        return result;
    }
}

package com.mbu.routex.trip.repository;

import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.entity.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByStatus(TripStatus status);
    List<Trip> findByBusId(Long busId);
    Optional<Trip> findFirstByBusIdAndStatusOrderByScheduledDepartureDesc(Long busId, TripStatus status);
    List<Trip> findByDriverId(Long driverId);
    List<Trip> findByDriverIdOrderByScheduledDepartureDesc(Long driverId);
}

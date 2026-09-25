package com.mbu.routex.bus.repository;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(String busNumber);
    List<Bus> findByStatus(BusStatus status);
    List<Bus> findByStatusNot(BusStatus status);
}

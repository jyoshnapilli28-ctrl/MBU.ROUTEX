package com.mbu.routex.route.repository;

import com.mbu.routex.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByRouteCode(String routeCode);
    List<Route> findByIsActiveTrue();
}

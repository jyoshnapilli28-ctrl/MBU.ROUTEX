package com.mbu.routex.route.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "route_stops", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"route_id", "stop_order"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(name = "stop_name", nullable = false, length = 255)
    private String stopName;

    @Column(name = "stop_order", nullable = false)
    private Integer stopOrder;

    @Column(name = "location_description", length = 500)
    private String locationDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

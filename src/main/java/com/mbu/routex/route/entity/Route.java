package com.mbu.routex.route.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_name", nullable = false, length = 255)
    private String routeName;

    @Column(name = "route_code", nullable = false, unique = true, length = 50)
    private String routeCode;

    @Column(name = "start_point", nullable = false, length = 255)
    private String startPoint;

    @Column(name = "end_point", nullable = false, length = 255)
    private String endPoint;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("stopOrder ASC")
    @Builder.Default
    private List<RouteStop> stops = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

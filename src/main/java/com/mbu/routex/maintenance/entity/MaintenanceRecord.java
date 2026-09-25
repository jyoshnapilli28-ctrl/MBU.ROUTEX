package com.mbu.routex.maintenance.entity;

import com.mbu.routex.bus.entity.Bus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Column(name = "component", nullable = false, length = 100)
    private String component;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;

    @Column(name = "next_service_date")
    private LocalDate nextServiceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MaintenanceStatus status;

    @Column(name = "recorded_by", nullable = false)
    private Long recordedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

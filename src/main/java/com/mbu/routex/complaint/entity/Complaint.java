package com.mbu.routex.complaint.entity;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.trip.entity.Trip;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submitted_by", nullable = false)
    private Long submittedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "submitter_type", nullable = false)
    private SubmitterType submitterType;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false)
    private ComplaintStatusEnum currentStatus = ComplaintStatusEnum.OPEN;

    @Column(name = "management_response", columnDefinition = "TEXT")
    private String managementResponse;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

package com.mbu.routex.attendance.entity;

import com.mbu.routex.student.entity.Student;
import com.mbu.routex.trip.entity.Trip;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "trip_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_code_id", nullable = false)
    private QRCode qrCode;

    @Column(name = "scanned_at", nullable = false)
    private LocalDateTime scannedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AttendanceStatus status = AttendanceStatus.PRESENT;

    @PrePersist
    protected void onCreate() {
        if (this.scannedAt == null) {
            this.scannedAt = LocalDateTime.now();
        }
    }
}

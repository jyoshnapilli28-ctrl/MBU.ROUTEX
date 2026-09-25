package com.mbu.routex.emergency.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emergency_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private EmergencyCategory category;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}

# MBU RouteX — Database Design

> **Status:** PLANNED  
> **Version:** 1.0

---

## Database Technology

| Property | Value |
|---|---|
| Database | MySQL |
| ORM | Hibernate (via Spring Data JPA) |
| Schema management | Hibernate DDL / Flyway (TO BE DECIDED) |
| Character set | utf8mb4 |
| Collation | utf8mb4_unicode_ci |

---

## Entity List

| Entity | Table Name | Description |
|---|---|---|
| `User` | `users` | Base user account (all roles) |
| `Student` | `students` | Student profile linked to user |
| `Driver` | `drivers` | Driver profile linked to user |
| `ManagementUser` | `management_users` | Management profile linked to user |
| `Bus` | `buses` | Physical bus records |
| `Route` | `routes` | Bus routes |
| `RouteStop` | `route_stops` | Individual stops on a route |
| `Trip` | `trips` | Scheduled/active trips |
| `BusAssignment` | `bus_assignments` | Bus-to-route-to-driver assignment |
| `StudentBusAssignment` | `student_bus_assignments` | Student-to-bus mapping |
| `DriverAssignment` | `driver_assignments` | Driver-to-bus mapping |
| `Complaint` | `complaints` | Student/driver complaints |
| `ComplaintStatus` | `complaint_statuses` | Status history per complaint |
| `MaintenanceRecord` | `maintenance_records` | Vehicle maintenance log |
| `Attendance` | `attendance` | QR attendance records |
| `QRCode` | `qr_codes` | Student QR code data |
| `Notification` | `notifications` | System notifications per user |
| `EmergencyContact` | `emergency_contacts` | Emergency contact directory |
| `Report` | `reports` | Generated report records |

---

## Core Design Principles

1. All tables use an auto-increment `id` (BIGINT) as primary key
2. Soft deletes preferred over hard deletes where data integrity is important
3. `created_at` and `updated_at` timestamp columns on all main entities
4. Foreign key constraints enforced at database level
5. Normalized to at minimum 3NF
6. Role is stored in `users.role` as an enum

---

## Entity Relationship Summary

```
User (1) ──── (1) Student
User (1) ──── (1) Driver
User (1) ──── (1) ManagementUser

Route (1) ──── (Many) RouteStop
Bus (1) ──── (Many) Trip
Bus (Many) ──── (1) Route     [via BusAssignment]
Bus (Many) ──── (1) Driver    [via DriverAssignment]
Bus (Many) ──── (Many) Student [via StudentBusAssignment]

Trip (1) ──── (Many) Attendance
Student (1) ──── (1) QRCode
Student (1) ──── (Many) Complaint
Driver (1) ──── (Many) Complaint
Management (1) ──── (Many) Report
```

---

## See Also

| Document | Location |
|---|---|
| Entity Relationships (ER Diagram) | `04-database/ENTITY_RELATIONSHIPS.md` |
| Database Schema (SQL) | `04-database/DATABASE_SCHEMA.md` |
| Data Dictionary | `04-database/DATA_DICTIONARY.md` |
| Database Rules | `04-database/DATABASE_RULES.md` |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

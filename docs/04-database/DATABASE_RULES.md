# MBU RouteX — Database Rules

> **Status:** PLANNED  
> **Version:** 1.0

---

## Integrity Rules

| Rule ID | Rule | Enforcement |
|---|---|---|
| DR-01 | Every user must have exactly one role | `users.role` ENUM NOT NULL |
| DR-02 | A student profile must link to a ROLE_STUDENT user | Application-level validation |
| DR-03 | A driver profile must link to a ROLE_DRIVER user | Application-level validation |
| DR-04 | A management profile must link to a ROLE_MANAGEMENT user | Application-level validation |
| DR-05 | A student can have only one active bus assignment at a time | Application-level validation |
| DR-06 | A bus can have only one active driver assignment at a time | Application-level validation |
| DR-07 | Route stops must have unique order per route | UNIQUE KEY on (route_id, stop_order) |
| DR-08 | A student can have only one attendance record per trip | UNIQUE KEY on (student_id, trip_id) |
| DR-09 | Each student has at most one QR code | UNIQUE on students.id in qr_codes |
| DR-10 | Passwords must never be stored in plain text | BCrypt enforcement in service layer |

---

## Referential Integrity

All foreign keys are enforced at the database level using `FOREIGN KEY` constraints.

| FK | Behaviour on Parent Delete |
|---|---|
| students.user_id → users.id | RESTRICT (user cannot be deleted while student profile exists) |
| drivers.user_id → users.id | RESTRICT |
| management_users.user_id → users.id | RESTRICT |
| route_stops.route_id → routes.id | CASCADE (stops deleted with route) |
| trips.bus_id → buses.id | RESTRICT |
| trips.route_id → routes.id | RESTRICT |
| trips.driver_id → drivers.id | RESTRICT |
| attendance.trip_id → trips.id | RESTRICT |
| attendance.student_id → students.id | RESTRICT |
| complaints.bus_id → buses.id | SET NULL (optional reference) |
| maintenance_records.bus_id → buses.id | RESTRICT |

---

## Business Rules

| Rule ID | Rule |
|---|---|
| BR-01 | A complaint may only be submitted by a ROLE_STUDENT or ROLE_DRIVER user |
| BR-02 | Management may view all complaints; students and drivers may only view their own |
| BR-03 | Complaint status transitions must follow: OPEN → IN_REVIEW → RESOLVED or ESCALATED |
| BR-04 | A trip may only be completed when actual_departure is recorded |
| BR-05 | Attendance can only be recorded for ACTIVE or COMPLETED trips |
| BR-06 | QR token must be a cryptographically secure unique value |
| BR-07 | Emergency contacts must have a non-empty phone value |
| BR-08 | Bus capacity must be a positive integer |
| BR-09 | Route must have at least 2 stops (start and end) |
| BR-10 | Report data may only be generated from stored application data |

---

## Soft Delete Policy

| Table | Soft Delete Column | Hard Delete |
|---|---|---|
| `users` | `is_active` | Not recommended — preserve audit trail |
| `buses` | `status = INACTIVE` | Not recommended |
| `routes` | `is_active` | Not recommended |
| `qr_codes` | `is_active` | Not recommended |
| `emergency_contacts` | `is_active` | Not recommended |
| All other tables | No soft delete — consult project owner | Restricted |

---

## Indexing Recommendations

| Table | Index | Reason |
|---|---|---|
| `users` | `username`, `email` | Login lookups |
| `students` | `student_id` | Student ID lookup |
| `complaints` | `current_status`, `created_at` | Filter and sort |
| `trips` | `status`, `scheduled_departure` | Active trip queries |
| `attendance` | `student_id`, `trip_id` | Attendance lookups |
| `notifications` | `user_id`, `is_read` | Unread notification filter |
| `maintenance_records` | `bus_id`, `next_service_date` | Upcoming maintenance |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

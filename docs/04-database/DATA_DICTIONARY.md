# MBU RouteX — Data Dictionary

> **Status:** PLANNED  
> **Version:** 1.0

---

## Table: users

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key, auto-increment |
| `username` | VARCHAR(100) | NO | Unique login username |
| `email` | VARCHAR(255) | NO | Unique email address |
| `password_hash` | VARCHAR(255) | NO | BCrypt hashed password |
| `role` | ENUM | NO | ROLE_STUDENT / ROLE_DRIVER / ROLE_MANAGEMENT |
| `is_active` | BOOLEAN | NO | Account active status; default TRUE |
| `created_at` | DATETIME | NO | Record creation timestamp |
| `updated_at` | DATETIME | NO | Last update timestamp |

---

## Table: students

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `user_id` | BIGINT | NO | FK → users.id (unique) |
| `student_id` | VARCHAR(50) | NO | University student ID number |
| `full_name` | VARCHAR(255) | NO | Student full name |
| `phone` | VARCHAR(20) | YES | Student phone (optional) |
| `department` | VARCHAR(100) | YES | Department / faculty |
| `year` | VARCHAR(20) | YES | Year of study |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: drivers

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `user_id` | BIGINT | NO | FK → users.id (unique) |
| `driver_id` | VARCHAR(50) | NO | Internal driver ID |
| `full_name` | VARCHAR(255) | NO | Driver full name |
| `phone` | VARCHAR(20) | YES | Driver phone |
| `license_number` | VARCHAR(100) | YES | Driving license number |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: management_users

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `user_id` | BIGINT | NO | FK → users.id (unique) |
| `full_name` | VARCHAR(255) | NO | Management user full name |
| `department` | VARCHAR(100) | YES | Management department |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: buses

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `bus_number` | VARCHAR(50) | NO | Unique bus identifier |
| `registration` | VARCHAR(100) | YES | Vehicle registration number |
| `college_serial` | VARCHAR(50) | YES | Internal college serial/fleet number |
| `capacity` | INT | NO | Maximum passenger capacity |
| `status` | ENUM | NO | ACTIVE / ON_ROUTE / DELAYED / STOPPED / MAINTENANCE / COMPLETED / INACTIVE |
| `created_at` | DATETIME | NO | Record creation timestamp |
| `updated_at` | DATETIME | NO | Last update timestamp |

---

## Table: routes

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `route_name` | VARCHAR(255) | NO | Descriptive route name |
| `route_code` | VARCHAR(50) | NO | Unique route code |
| `start_point` | VARCHAR(255) | NO | Route start location |
| `end_point` | VARCHAR(255) | NO | Route end location |
| `is_active` | BOOLEAN | NO | Whether route is currently active |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: route_stops

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `route_id` | BIGINT | NO | FK → routes.id |
| `stop_name` | VARCHAR(255) | NO | Name of the stop |
| `stop_order` | INT | NO | Sequential order within route (1, 2, 3…) |
| `location_description` | VARCHAR(500) | YES | Human-readable location description |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: trips

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `bus_id` | BIGINT | NO | FK → buses.id |
| `route_id` | BIGINT | NO | FK → routes.id |
| `driver_id` | BIGINT | NO | FK → drivers.id |
| `scheduled_departure` | DATETIME | NO | Planned departure time |
| `actual_departure` | DATETIME | YES | Actual departure time |
| `scheduled_arrival` | DATETIME | YES | Planned arrival time |
| `actual_arrival` | DATETIME | YES | Actual arrival time |
| `status` | ENUM | NO | SCHEDULED / ACTIVE / ON_ROUTE / DELAYED / COMPLETED / CANCELLED |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: complaints

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `submitted_by` | BIGINT | NO | User ID of submitter (student or driver) |
| `submitter_type` | ENUM | NO | STUDENT or DRIVER |
| `category` | VARCHAR(100) | NO | Complaint category |
| `description` | TEXT | NO | Complaint full description |
| `bus_id` | BIGINT | YES | FK → buses.id (if bus-related) |
| `trip_id` | BIGINT | YES | FK → trips.id (if trip-related) |
| `current_status` | ENUM | NO | OPEN / IN_REVIEW / RESOLVED / ESCALATED |
| `management_response` | TEXT | YES | Response from management |
| `created_at` | DATETIME | NO | Submission timestamp |
| `updated_at` | DATETIME | NO | Last update timestamp |

---

## Table: maintenance_records

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `bus_id` | BIGINT | NO | FK → buses.id |
| `component` | VARCHAR(100) | NO | Component serviced (Tyre, Brake, Engine, etc.) |
| `description` | TEXT | YES | Detailed description of work |
| `service_date` | DATE | NO | Date service was performed |
| `next_service_date` | DATE | YES | Next scheduled service date |
| `status` | ENUM | NO | SCHEDULED / IN_PROGRESS / COMPLETED / OVERDUE |
| `recorded_by` | BIGINT | NO | Management user who recorded this |
| `created_at` | DATETIME | NO | Record creation timestamp |

---

## Table: qr_codes

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `student_id` | BIGINT | NO | FK → students.id (unique) |
| `qr_token` | VARCHAR(512) | NO | Unique token encoded in the QR |
| `is_active` | BOOLEAN | NO | Whether QR is currently valid |
| `created_at` | DATETIME | NO | Creation timestamp |
| `expires_at` | DATETIME | YES | Expiration timestamp (if applicable) |

---

## Table: attendance

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `student_id` | BIGINT | NO | FK → students.id |
| `trip_id` | BIGINT | NO | FK → trips.id |
| `qr_code_id` | BIGINT | NO | FK → qr_codes.id |
| `scanned_at` | DATETIME | NO | Timestamp of scan |
| `status` | ENUM | NO | PRESENT / ABSENT / LATE |

---

## Table: emergency_contacts

| Column | Type | Nullable | Description |
|---|---|---|---|
| `id` | BIGINT | NO | Primary key |
| `name` | VARCHAR(255) | NO | Contact name / department name |
| `role` | VARCHAR(100) | NO | Role / title of contact |
| `phone` | VARCHAR(30) | NO | Contact phone number |
| `category` | ENUM | NO | TRANSPORT / CAMPUS / MEDICAL / OTHER |
| `is_active` | BOOLEAN | NO | Whether contact is currently active |

---

## Enum Value Reference

| Enum | Values |
|---|---|
| `users.role` | ROLE_STUDENT, ROLE_DRIVER, ROLE_MANAGEMENT |
| `buses.status` | ACTIVE, ON_ROUTE, DELAYED, STOPPED, MAINTENANCE, COMPLETED, INACTIVE |
| `trips.status` | SCHEDULED, ACTIVE, ON_ROUTE, DELAYED, COMPLETED, CANCELLED |
| `complaints.current_status` | OPEN, IN_REVIEW, RESOLVED, ESCALATED |
| `maintenance_records.status` | SCHEDULED, IN_PROGRESS, COMPLETED, OVERDUE |
| `attendance.status` | PRESENT, ABSENT, LATE |
| `emergency_contacts.category` | TRANSPORT, CAMPUS, MEDICAL, OTHER |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

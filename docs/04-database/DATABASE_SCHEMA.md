# MBU RouteX — Database Schema

> **Status:** PLANNED  
> **Version:** 1.0  
> **Database:** MySQL

---

## users

```sql
CREATE TABLE users (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(100) NOT NULL UNIQUE,
    email          VARCHAR(255) NOT NULL UNIQUE,
    password_hash  VARCHAR(255) NOT NULL,
    role           ENUM('ROLE_STUDENT', 'ROLE_DRIVER', 'ROLE_MANAGEMENT') NOT NULL,
    is_active      BOOLEAN NOT NULL DEFAULT TRUE,
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## students

```sql
CREATE TABLE students (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT NOT NULL UNIQUE,
    student_id   VARCHAR(50) NOT NULL UNIQUE,
    full_name    VARCHAR(255) NOT NULL,
    phone        VARCHAR(20),
    department   VARCHAR(100),
    year         VARCHAR(20),
    created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_students_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## drivers

```sql
CREATE TABLE drivers (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL UNIQUE,
    driver_id       VARCHAR(50) NOT NULL UNIQUE,
    full_name       VARCHAR(255) NOT NULL,
    phone           VARCHAR(20),
    license_number  VARCHAR(100),
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_drivers_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## management_users

```sql
CREATE TABLE management_users (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL UNIQUE,
    full_name   VARCHAR(255) NOT NULL,
    department  VARCHAR(100),
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mgmt_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## buses

```sql
CREATE TABLE buses (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_number     VARCHAR(50) NOT NULL UNIQUE,
    registration   VARCHAR(100),
    college_serial VARCHAR(50),
    capacity       INT NOT NULL,
    status         ENUM('ACTIVE','ON_ROUTE','DELAYED','STOPPED','MAINTENANCE','COMPLETED','INACTIVE')
                   NOT NULL DEFAULT 'ACTIVE',
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## routes

```sql
CREATE TABLE routes (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    route_name   VARCHAR(255) NOT NULL,
    route_code   VARCHAR(50) NOT NULL UNIQUE,
    start_point  VARCHAR(255) NOT NULL,
    end_point    VARCHAR(255) NOT NULL,
    is_active    BOOLEAN NOT NULL DEFAULT TRUE,
    created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## route_stops

```sql
CREATE TABLE route_stops (
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    route_id             BIGINT NOT NULL,
    stop_name            VARCHAR(255) NOT NULL,
    stop_order           INT NOT NULL,
    location_description VARCHAR(500),
    created_at           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_stop_route FOREIGN KEY (route_id) REFERENCES routes(id),
    UNIQUE KEY uq_route_stop_order (route_id, stop_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## trips

```sql
CREATE TABLE trips (
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_id               BIGINT NOT NULL,
    route_id             BIGINT NOT NULL,
    driver_id            BIGINT NOT NULL,
    scheduled_departure  DATETIME NOT NULL,
    actual_departure     DATETIME,
    scheduled_arrival    DATETIME,
    actual_arrival       DATETIME,
    status               ENUM('SCHEDULED','ACTIVE','ON_ROUTE','DELAYED','COMPLETED','CANCELLED')
                         NOT NULL DEFAULT 'SCHEDULED',
    created_at           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_trip_bus    FOREIGN KEY (bus_id)    REFERENCES buses(id),
    CONSTRAINT fk_trip_route  FOREIGN KEY (route_id)  REFERENCES routes(id),
    CONSTRAINT fk_trip_driver FOREIGN KEY (driver_id) REFERENCES drivers(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## bus_assignments

```sql
CREATE TABLE bus_assignments (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_id         BIGINT NOT NULL,
    route_id       BIGINT NOT NULL,
    effective_from DATE NOT NULL,
    effective_to   DATE,
    is_active      BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_ba_bus   FOREIGN KEY (bus_id)   REFERENCES buses(id),
    CONSTRAINT fk_ba_route FOREIGN KEY (route_id) REFERENCES routes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## driver_assignments

```sql
CREATE TABLE driver_assignments (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    driver_id      BIGINT NOT NULL,
    bus_id         BIGINT NOT NULL,
    effective_from DATE NOT NULL,
    effective_to   DATE,
    is_active      BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_da_driver FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT fk_da_bus    FOREIGN KEY (bus_id)    REFERENCES buses(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## student_bus_assignments

```sql
CREATE TABLE student_bus_assignments (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id       BIGINT NOT NULL,
    bus_id           BIGINT NOT NULL,
    boarding_stop_id BIGINT,
    effective_from   DATE NOT NULL,
    effective_to     DATE,
    is_active        BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_sba_student FOREIGN KEY (student_id)       REFERENCES students(id),
    CONSTRAINT fk_sba_bus     FOREIGN KEY (bus_id)           REFERENCES buses(id),
    CONSTRAINT fk_sba_stop    FOREIGN KEY (boarding_stop_id) REFERENCES route_stops(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## complaints

```sql
CREATE TABLE complaints (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    submitted_by        BIGINT NOT NULL,
    submitter_type      ENUM('STUDENT','DRIVER') NOT NULL,
    category            VARCHAR(100) NOT NULL,
    description         TEXT NOT NULL,
    bus_id              BIGINT,
    trip_id             BIGINT,
    current_status      ENUM('OPEN','IN_REVIEW','RESOLVED','ESCALATED') NOT NULL DEFAULT 'OPEN',
    management_response TEXT,
    created_at          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_complaint_bus  FOREIGN KEY (bus_id)  REFERENCES buses(id),
    CONSTRAINT fk_complaint_trip FOREIGN KEY (trip_id) REFERENCES trips(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## complaint_statuses

```sql
CREATE TABLE complaint_statuses (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    complaint_id BIGINT NOT NULL,
    status       ENUM('OPEN','IN_REVIEW','RESOLVED','ESCALATED') NOT NULL,
    changed_by   BIGINT NOT NULL,
    notes        TEXT,
    changed_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cs_complaint FOREIGN KEY (complaint_id) REFERENCES complaints(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## maintenance_records

```sql
CREATE TABLE maintenance_records (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_id           BIGINT NOT NULL,
    component        VARCHAR(100) NOT NULL,
    description      TEXT,
    service_date     DATE NOT NULL,
    next_service_date DATE,
    status           ENUM('SCHEDULED','IN_PROGRESS','COMPLETED','OVERDUE') NOT NULL,
    recorded_by      BIGINT NOT NULL,
    created_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mr_bus FOREIGN KEY (bus_id) REFERENCES buses(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## qr_codes

```sql
CREATE TABLE qr_codes (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id  BIGINT NOT NULL UNIQUE,
    qr_token    VARCHAR(512) NOT NULL UNIQUE,
    is_active   BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at  DATETIME,
    CONSTRAINT fk_qr_student FOREIGN KEY (student_id) REFERENCES students(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## attendance

```sql
CREATE TABLE attendance (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id  BIGINT NOT NULL,
    trip_id     BIGINT NOT NULL,
    qr_code_id  BIGINT NOT NULL,
    scanned_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status      ENUM('PRESENT','ABSENT','LATE') NOT NULL DEFAULT 'PRESENT',
    CONSTRAINT fk_att_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_att_trip    FOREIGN KEY (trip_id)    REFERENCES trips(id),
    CONSTRAINT fk_att_qr      FOREIGN KEY (qr_code_id) REFERENCES qr_codes(id),
    UNIQUE KEY uq_attendance_student_trip (student_id, trip_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## notifications

```sql
CREATE TABLE notifications (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    title      VARCHAR(255) NOT NULL,
    message    TEXT NOT NULL,
    is_read    BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_notif_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## emergency_contacts

```sql
CREATE TABLE emergency_contacts (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    role      VARCHAR(100) NOT NULL,
    phone     VARCHAR(30) NOT NULL,
    category  ENUM('TRANSPORT','CAMPUS','MEDICAL','OTHER') NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## reports

```sql
CREATE TABLE reports (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    generated_by   BIGINT NOT NULL,
    report_type    VARCHAR(100) NOT NULL,
    period_from    DATE,
    period_to      DATE,
    report_data    LONGTEXT,
    generated_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_report_mgmt FOREIGN KEY (generated_by) REFERENCES management_users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

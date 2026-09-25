# MBU RouteX — IMPLEMENTATION PLAN

> **Status:** ACTIVE — Follow this document step by step. Do NOT skip steps.  
> **Version:** 1.0  
> **Last Updated:** 2026-09-24  
> **Project:** MBU RouteX — TRACK · TRAVEL · CONNECT

---

## MANDATORY RULES — READ BEFORE ANY WORK

```
RULE 1:  Follow phases IN ORDER. Do NOT jump ahead.
RULE 2:  Complete EVERY task in a phase before moving to the next phase.
RULE 3:  The UI is LOCKED. Do NOT redesign anything.
RULE 4:  Roles are STUDENT, DRIVER, MANAGEMENT. Never use FACULTY, ADMIN, or USER.
RULE 5:  Root package is com.mbu.routex — never change this.
RULE 6:  Database is MySQL — never use PostgreSQL, H2, or anything else.
RULE 7:  Template engine is Thymeleaf — never use JSP, React, or Angular.
RULE 8:  CSS is already written in assets/styles/ — never use Tailwind, Bootstrap, or Material.
RULE 9:  Use ONLY synthetic demo data. Never invent real personal data.
RULE 10: Color palette is LOCKED. Primary is #577376. Never use neon, gradients, or glow.
RULE 11: Every page has the SAME taskbar: Logo | Home | Notifications | Track Updates | Profile.
RULE 12: Every dashboard has the SAME hero: campus-hero.jpg + rgba(87,115,118,0.55) overlay.
RULE 13: Logo file is assets/brand/logo-primary.png (supplied by project owner).
RULE 14: Font is Inter from Google Fonts. No other fonts.
RULE 15: When in doubt, check docs/ folder. If docs say X, do X. Never override docs.
```

---

## PHASE 0 — PROJECT INITIALIZATION

**Goal:** Create the Spring Boot project skeleton with correct dependencies.

### Task 0.1 — Create Spring Boot Project

Use Spring Initializr or Maven archetype with these EXACT settings:

| Setting | Value |
|---|---|
| Group ID | `com.mbu` |
| Artifact ID | `routex` |
| Name | `MBU RouteX` |
| Package Name | `com.mbu.routex` |
| Java Version | 17 |
| Spring Boot Version | 3.2.x or 3.3.x (latest stable 3.x) |
| Packaging | JAR |
| Build Tool | Maven |

### Task 0.2 — Add Dependencies to pom.xml

Add exactly these dependencies. Do NOT add anything else unless a later phase tells you to.

```xml
<dependencies>
    <!-- Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Thymeleaf + Security integration -->
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-springsecurity6</artifactId>
    </dependency>

    <!-- JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok (optional but recommended) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- DevTools (development only) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Task 0.3 — Create application.properties

File: `src/main/resources/application.properties`

```properties
# Application
spring.application.name=MBU RouteX

# Server
server.port=8080

# Database — MySQL ONLY
spring.datasource.url=jdbc:mysql://localhost:3306/mbu_routex?useSSL=false&serverTimezone=Asia/Kolkata
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Logging
logging.level.com.mbu.routex=DEBUG
logging.level.org.springframework.security=INFO
```

### Task 0.4 — Copy Asset Files

Copy the entire `assets/` folder to:

```
src/main/resources/static/assets/
```

This makes all CSS, icons, images, and brand files accessible at `/assets/...` in the browser.

### Task 0.5 — Create MySQL Database

```sql
CREATE DATABASE IF NOT EXISTS mbu_routex
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```

### Task 0.6 — Verify

Run the application. It should start on port 8080 without errors (security will redirect to /login by default — this is expected).

**✅ PHASE 0 COMPLETE when:** Application starts, connects to MySQL, shows Spring Boot startup logs.

---

## PHASE 1 — DATABASE ENTITIES AND REPOSITORIES

**Goal:** Create all JPA entities matching the database schema exactly.

### Task 1.1 — Create Package Structure

Create these packages under `com.mbu.routex`:

```
config/
common/exception/
common/util/
auth/controller/
auth/service/
auth/dto/
user/entity/
user/repository/
student/entity/
student/repository/
student/controller/
student/service/
student/dto/
driver/entity/
driver/repository/
driver/controller/
driver/service/
driver/dto/
management/entity/
management/repository/
management/controller/
management/service/
management/dto/
bus/entity/
bus/repository/
route/entity/
route/repository/
trip/entity/
trip/repository/
complaint/entity/
complaint/repository/
maintenance/entity/
maintenance/repository/
attendance/entity/
attendance/repository/
notification/entity/
notification/repository/
emergency/entity/
emergency/repository/
```

### Task 1.2 — Create Enum Types

File: `com/mbu/routex/user/entity/Role.java`
```java
public enum Role {
    ROLE_STUDENT,
    ROLE_DRIVER,
    ROLE_MANAGEMENT
}
```

> **NEVER add ROLE_FACULTY, ROLE_ADMIN, or ROLE_USER. Only these three exist.**

File: `com/mbu/routex/bus/entity/BusStatus.java`
```java
public enum BusStatus {
    ACTIVE, ON_ROUTE, DELAYED, STOPPED, MAINTENANCE, COMPLETED, INACTIVE
}
```

File: `com/mbu/routex/trip/entity/TripStatus.java`
```java
public enum TripStatus {
    SCHEDULED, ACTIVE, ON_ROUTE, DELAYED, COMPLETED, CANCELLED
}
```

File: `com/mbu/routex/complaint/entity/ComplaintStatusEnum.java`
```java
public enum ComplaintStatusEnum {
    OPEN, IN_REVIEW, RESOLVED, ESCALATED
}
```

File: `com/mbu/routex/maintenance/entity/MaintenanceStatus.java`
```java
public enum MaintenanceStatus {
    SCHEDULED, IN_PROGRESS, COMPLETED, OVERDUE
}
```

File: `com/mbu/routex/attendance/entity/AttendanceStatus.java`
```java
public enum AttendanceStatus {
    PRESENT, ABSENT, LATE
}
```

File: `com/mbu/routex/complaint/entity/SubmitterType.java`
```java
public enum SubmitterType {
    STUDENT, DRIVER
}
```

File: `com/mbu/routex/emergency/entity/EmergencyCategory.java`
```java
public enum EmergencyCategory {
    TRANSPORT, CAMPUS, MEDICAL, OTHER
}
```

### Task 1.3 — Create Entity Classes

Create one entity class per table. Each entity MUST match the column names and types in `docs/04-database/DATABASE_SCHEMA.md` EXACTLY.

**Entities to create (19 total):**

| # | Entity Class | Package | DB Table |
|---|---|---|---|
| 1 | `User` | `user.entity` | `users` |
| 2 | `Student` | `student.entity` | `students` |
| 3 | `Driver` | `driver.entity` | `drivers` |
| 4 | `ManagementUser` | `management.entity` | `management_users` |
| 5 | `Bus` | `bus.entity` | `buses` |
| 6 | `Route` | `route.entity` | `routes` |
| 7 | `RouteStop` | `route.entity` | `route_stops` |
| 8 | `Trip` | `trip.entity` | `trips` |
| 9 | `BusAssignment` | `bus.entity` | `bus_assignments` |
| 10 | `DriverAssignment` | `driver.entity` | `driver_assignments` |
| 11 | `StudentBusAssignment` | `student.entity` | `student_bus_assignments` |
| 12 | `Complaint` | `complaint.entity` | `complaints` |
| 13 | `ComplaintStatus` | `complaint.entity` | `complaint_statuses` |
| 14 | `MaintenanceRecord` | `maintenance.entity` | `maintenance_records` |
| 15 | `QRCode` | `attendance.entity` | `qr_codes` |
| 16 | `Attendance` | `attendance.entity` | `attendance` |
| 17 | `Notification` | `notification.entity` | `notifications` |
| 18 | `EmergencyContact` | `emergency.entity` | `emergency_contacts` |
| 19 | `Report` | `management.entity` | `reports` |

### Task 1.4 — Create Repository Interfaces

One Spring Data JPA repository per entity. Example pattern:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
```

**✅ PHASE 1 COMPLETE when:** All 19 entities compile, all repositories compile, and `ddl-auto=update` creates all tables in MySQL.

---

## PHASE 2 — SECURITY AND AUTHENTICATION

**Goal:** Spring Security with form-based login, role-based access, and separate login pages per role.

### Task 2.1 — CustomUserDetailsService

File: `auth/service/CustomUserDetailsService.java`

- Implements `UserDetailsService`
- Loads `User` by username from `UserRepository`
- Returns `UserDetails` with the user's role as a granted authority
- The role stored in DB is already prefixed: `ROLE_STUDENT`, `ROLE_DRIVER`, `ROLE_MANAGEMENT`

### Task 2.2 — SecurityConfig

File: `config/SecurityConfig.java`

```
URL Access Rules:

  /                     → permitAll (homepage)
  /login/**             → permitAll (login pages)
  /assets/**            → permitAll (static assets)
  /student/**           → hasRole('STUDENT')
  /driver/**            → hasRole('DRIVER')
  /management/**        → hasRole('MANAGEMENT')
  everything else       → authenticated
```

Login configuration:
- Login page: `/login`
- Login processing URL: `/login/process`
- Success handler: redirect based on role:
  - `ROLE_STUDENT` → `/student/dashboard`
  - `ROLE_DRIVER` → `/driver/dashboard`
  - `ROLE_MANAGEMENT` → `/management/dashboard`
- Logout URL: `/logout` → redirect to `/`

### Task 2.3 — AuthController

File: `auth/controller/AuthController.java`

```
GET  /login                → login role selection page
GET  /login/student        → student login form
GET  /login/driver         → driver login form
GET  /login/management     → management login form
POST /login/process        → Spring Security handles this
```

### Task 2.4 — Password Encoding

Use `BCryptPasswordEncoder` as the `PasswordEncoder` bean in `SecurityConfig`.

### Task 2.5 — Create Demo Users

Use a `CommandLineRunner` or `ApplicationRunner` bean.

Create exactly these demo accounts:

| Username | Password (raw) | Role | Profile |
|---|---|---|---|
| `student1` | `password` | `ROLE_STUDENT` | Name: Ravi Kumar, ID: STU001, Dept: Computer Science, Year: 3rd |
| `student2` | `password` | `ROLE_STUDENT` | Name: Priya Sharma, ID: STU002, Dept: Electronics, Year: 2nd |
| `driver1` | `password` | `ROLE_DRIVER` | Name: Suresh Patil, ID: DRV001, License: MH12-DL-2020-1234 |
| `driver2` | `password` | `ROLE_DRIVER` | Name: Ramesh Yadav, ID: DRV002, License: MH12-DL-2019-5678 |
| `management1` | `password` | `ROLE_MANAGEMENT` | Name: Dr. Anita Desai, Dept: Transport Management |

> Passwords must be BCrypt-encoded when stored.

**✅ PHASE 2 COMPLETE when:** You can log in as student1/driver1/management1 and each redirects to their correct dashboard URL.

---

## PHASE 3 — THYMELEAF TEMPLATES: HOMEPAGE AND LOGIN

**Goal:** Build the public homepage and login pages matching the locked UI specification exactly.

### Task 3.1 — Create Shared Thymeleaf Fragments

File: `templates/fragments/head.html`

Contains the `<head>` content shared by ALL pages:
- Meta charset UTF-8, viewport for responsive
- Title: `MBU RouteX — TRACK · TRAVEL · CONNECT`
- Favicon link: `/assets/brand/favicon.svg`
- Google Fonts Inter import (preconnect + stylesheet)
- All CSS files from `assets/styles/` in this EXACT order:
  1. `variables.css`
  2. `colors.css`
  3. `typography.css`
  4. `spacing.css`
  5. `radius.css`
  6. `shadows.css`
  7. `borders.css`
  8. `components.css`
  9. `status.css`
  10. `responsive.css`

### Task 3.2 — Create Taskbar Fragment

File: `templates/fragments/taskbar.html`

IDENTICAL on every authenticated page:

```html
<nav class="taskbar" th:fragment="taskbar(role)">
  <a th:href="@{/}" class="taskbar-logo">
    <img th:src="@{/assets/brand/logo-primary.png}" alt="MBU RouteX" height="36">
  </a>
  <div class="taskbar-nav">
    <a th:href="@{/${role}/dashboard}" class="taskbar-link">
      <img th:src="@{/assets/icons/navigation/home.svg}" width="20" height="20"> Home
    </a>
    <a th:href="@{/${role}/notifications}" class="taskbar-link">
      <img th:src="@{/assets/icons/navigation/notifications.svg}" width="20" height="20"> Notifications
    </a>
    <a th:href="@{/${role}/track-updates}" class="taskbar-link">
      <img th:src="@{/assets/icons/navigation/track-updates.svg}" width="20" height="20"> Track Updates
    </a>
    <a th:href="@{/${role}/profile}" class="taskbar-link">
      <img th:src="@{/assets/icons/navigation/profile.svg}" width="20" height="20"> Profile
    </a>
  </div>
</nav>
```

### Task 3.3 — Create Hero Fragment

File: `templates/fragments/hero.html`

```html
<section class="hero" th:fragment="hero(greeting)">
  <img th:src="@{/assets/images/hero/campus-hero.jpg}" alt="MBU Campus" class="hero-image">
  <div class="hero-overlay"></div>
  <div class="hero-content">
    <h1 th:text="${greeting}" class="hero-title">Welcome</h1>
  </div>
</section>
```

The `greeting` variable values per role:
- Homepage: not used (hero has logo + tagline instead)
- Student Dashboard: `Hello MBUIans !!!`
- Driver Dashboard: `Hello Drivers !!!`
- Management Dashboard: `Welcome Management !!!`

### Task 3.4 — Create Homepage Template

File: `templates/index.html`

**Structure:**
1. Hero section: campus photo + teal overlay + MBU RouteX logo (large) + `TRACK · TRAVEL · CONNECT` tagline + `DREAM BELIEVE ACHIEVE` text + optional scroll indicator
2. Role selection section with 3 cards in a row:
   - Card 1: Student icon + "STUDENT" + "Student Portal" + Login button → `/login/student`
   - Card 2: Driver icon + "DRIVER" + "Driver Portal" + Login button → `/login/driver`
   - Card 3: Management icon + "MANAGEMENT" + "Management Portal" + Login button → `/login/management`

### Task 3.5 — Create Login Templates

Three separate login pages. Same layout, different role label:

- `templates/login/student-login.html` — heading "Student Login"
- `templates/login/driver-login.html` — heading "Driver Login"
- `templates/login/management-login.html` — heading "Management Login"

Each page contains:
- MBU RouteX logo (centered)
- Role heading
- Form with `action="/login/process"` `method="POST"`:
  - Username text input
  - Password password input
  - Hidden field for role (optional, for redirect)
  - Login button (class `btn btn-primary`, background `#577376`)
- Error message div (shown when `param.error` exists)
- "Back to Home" link → `/`

### Task 3.6 — HomeController

File: `auth/controller/HomeController.java`

```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
```

**✅ PHASE 3 COMPLETE when:** Homepage shows hero + 3 role cards. Clicking a card → login form. Successful login → dashboard.

---

## PHASE 4 — DASHBOARD TEMPLATES

**Goal:** Build all three role dashboards with correct greetings, module cards, and icons.

### Task 4.1 — Student Dashboard

**URL:** `GET /student/dashboard`  
**Controller:** `StudentBusController.java`  
**Template:** `templates/student/dashboard.html`

Module cards (5 cards):

| # | Card Label | Link | Icon Path |
|---|---|---|---|
| 1 | My Bus | `/student/my-bus` | `/assets/icons/student/my-bus.svg` |
| 2 | Live Track | `/student/live-track` | `/assets/icons/student/live-track.svg` |
| 3 | Complaint | `/student/complaint` | `/assets/icons/student/complaint.svg` |
| 4 | Emergency | `/student/emergency` | `/assets/icons/student/emergency.svg` |
| 5 | Attendance – My QR | `/student/attendance` | `/assets/icons/student/attendance-qr.svg` |

### Task 4.2 — Driver Dashboard

**URL:** `GET /driver/dashboard`  
**Controller:** `DriverStatusController.java`  
**Template:** `templates/driver/dashboard.html`

Module cards (5 cards):

| # | Card Label | Link | Icon Path |
|---|---|---|---|
| 1 | Bus Status | `/driver/bus-status` | `/assets/icons/driver/bus-status.svg` |
| 2 | Service Details | `/driver/service-details` | `/assets/icons/driver/service-details.svg` |
| 3 | Bus Complaints | `/driver/bus-complaints` | `/assets/icons/driver/bus-complaints.svg` |
| 4 | Schedules | `/driver/schedules` | `/assets/icons/driver/schedule.svg` |
| 5 | Emergency | `/driver/emergency` | `/assets/icons/driver/driver-emergency.svg` |

### Task 4.3 — Management Dashboard

**URL:** `GET /management/dashboard`  
**Controller:** `ManagementBusController.java`  
**Template:** `templates/management/dashboard.html`

Module cards (7 cards):

| # | Card Label | Link | Icon Path |
|---|---|---|---|
| 1 | Bus Details | `/management/bus-details` | `/assets/icons/management/bus-details.svg` |
| 2 | Trip Management | `/management/trips` | `/assets/icons/management/trip-management.svg` |
| 3 | Complaints | `/management/complaints` | `/assets/icons/management/management-complaints.svg` |
| 4 | Maintenance | `/management/maintenance` | `/assets/icons/management/maintenance.svg` |
| 5 | Reports | `/management/reports` | `/assets/icons/management/reports.svg` |
| 6 | Student Overview | `/management/students` | `/assets/icons/management/student-overview.svg` |
| 7 | Module 7 (placeholder) | `#` (disabled) | `/assets/icons/management/management-module-placeholder.svg` |

**✅ PHASE 4 COMPLETE when:** All 3 dashboards render with correct greeting, correct cards, and correct icons.

---

## PHASE 5 — STUDENT MODULES (5 modules)

### Task 5.1 — My Bus

| | Value |
|---|---|
| URL | `GET /student/my-bus` |
| Controller | `StudentBusController` |
| Service | `StudentBusService` |
| Template | `templates/student/my-bus.html` |

**Data shown:** Student's assigned bus number, route name/code, driver name, departure times, list of stops, bus status.

**Data query path:** Current user → `StudentBusAssignment` (active) → `Bus` → `BusAssignment` → `Route` → `RouteStop` list → `DriverAssignment` → `Driver`

### Task 5.2 — Live Track

| | Value |
|---|---|
| URL | `GET /student/live-track` |
| Controller | `StudentTrackController` |
| Service | `StudentTrackService` |
| Template | `templates/student/live-track.html` |

**Data shown:** Active trip for student's bus, current stop, next stop, ETA, trip status badge (color-coded).

### Task 5.3 — Complaint

| | Value |
|---|---|
| URL | `GET /student/complaint` and `POST /student/complaint` |
| Controller | `StudentComplaintController` |
| Service | `StudentComplaintService` |
| Template | `templates/student/complaint.html` |

**Form fields:** Category dropdown, description textarea, submit button.

**Categories (EXACT strings):** `Delay`, `Driver behaviour`, `Bus condition`, `Overcrowding`, `Route issue`, `Cleanliness`, `Other`

**Below form:** Table of student's own complaints: date, category, status badge, bus number.

### Task 5.4 — Emergency

| | Value |
|---|---|
| URL | `GET /student/emergency` |
| Controller | `StudentEmergencyController` |
| Template | `templates/student/emergency.html` |

**Data shown:** Emergency contacts grouped by category (TRANSPORT, CAMPUS, MEDICAL). Phone as `tel:` links.

### Task 5.5 — Attendance / My QR

| | Value |
|---|---|
| URL | `GET /student/attendance` |
| Controller | `StudentAttendanceController` |
| Service | `StudentAttendanceService` |
| Template | `templates/student/attendance.html` |

**Data shown:** QR code image (generated using ZXing from `qr_codes.qr_token`), active status, attendance history table.

**Additional dependency needed in pom.xml:**
```xml
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.5.3</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.5.3</version>
</dependency>
```

**QR endpoint:** `GET /student/attendance/qr-image` → returns PNG image of QR code

**✅ PHASE 5 COMPLETE when:** All 5 student pages render, complaint form submits, QR code displays.

---

## PHASE 6 — DRIVER MODULES (5 modules)

### Task 6.1 — Bus Status

| | Value |
|---|---|
| URL | `GET /driver/bus-status` and `POST /driver/bus-status/update` |
| Controller | `DriverStatusController` |
| Service | `DriverStatusService` |
| Template | `templates/driver/bus-status.html` |

**Shows:** Current bus status badge, dropdown to change status (ACTIVE, ON_ROUTE, DELAYED, STOPPED, MAINTENANCE, COMPLETED), update button.

### Task 6.2 — Service Details

| | Value |
|---|---|
| URL | `GET /driver/service-details` |
| Controller | `DriverServiceController` |
| Service | `DriverServiceDetailsService` |
| Template | `templates/driver/service-details.html` |

**Shows:** Assigned bus info, route details with ordered stops, driver info, current trip, student count.

### Task 6.3 — Bus Complaints

| | Value |
|---|---|
| URL | `GET /driver/bus-complaints` and `POST /driver/bus-complaints` |
| Controller | `DriverComplaintController` |
| Service | `DriverComplaintService` |
| Template | `templates/driver/bus-complaints.html` |

**Form categories (EXACT):** `Tyre problem`, `Brake problem`, `Engine problem`, `Mechanical issue`, `Cleanliness`, `Bus damage`, `Other`

### Task 6.4 — Schedules

| | Value |
|---|---|
| URL | `GET /driver/schedules` |
| Controller | `DriverScheduleController` |
| Service | `DriverScheduleService` |
| Template | `templates/driver/schedules.html` |

**Shows:** Today's trips table, upcoming trips, route stop timings.

### Task 6.5 — Emergency

| | Value |
|---|---|
| URL | `GET /driver/emergency` |
| Controller | `DriverEmergencyController` |
| Template | `templates/driver/emergency.html` |

Same structure as student emergency.

**✅ PHASE 6 COMPLETE when:** All 5 driver pages render, bus status updates, bus complaint form submits.

---

## PHASE 7 — MANAGEMENT MODULES (6 + placeholder)

### Task 7.1 — Bus Details

| | Value |
|---|---|
| URL | `GET /management/bus-details` |
| Controller | `ManagementBusController` |
| Service | `ManagementBusService` |
| Template | `templates/management/bus-details.html` |

**Shows:** Table of all buses: number, registration, capacity, status, route, driver, student count. Filter by status.

### Task 7.2 — Trip Management

| | Value |
|---|---|
| URL | `GET /management/trips` |
| Controller | `ManagementTripController` |
| Service | `ManagementTripService` |
| Template | `templates/management/trips.html` |

**Shows:** All trips table: bus, route, driver, departure, status. Filter by status/date.

### Task 7.3 — Complaints

| | Value |
|---|---|
| URL | `GET /management/complaints` and `POST /management/complaints/{id}/update` |
| Controller | `ManagementComplaintController` |
| Service | `ManagementComplaintService` |
| Template | `templates/management/complaints.html` |

**Shows:** All complaints with filters. Detail view: full text, status history, response textarea, status update dropdown (OPEN → IN_REVIEW → RESOLVED / ESCALATED).

### Task 7.4 — Maintenance

| | Value |
|---|---|
| URL | `GET /management/maintenance` and `POST /management/maintenance/add` |
| Controller | `ManagementMaintenanceController` |
| Service | `ManagementMaintenanceService` |
| Template | `templates/management/maintenance.html` |

**Shows:** All records. Add new form: bus selector, component, description, service date, status.

### Task 7.5 — Reports

| | Value |
|---|---|
| URL | `GET /management/reports` |
| Controller | `ManagementReportController` |
| Service | `ManagementReportService` |
| Template | `templates/management/reports.html` |

**Shows:** Report type selector, date range, generate. Output as table.

### Task 7.6 — Student Overview

| | Value |
|---|---|
| URL | `GET /management/students` |
| Controller | `ManagementStudentController` |
| Service | `ManagementStudentService` |
| Template | `templates/management/students.html` |

**Shows:** Students grouped by bus. Counts per bus and route. Attendance summary.

### Task 7.7 — Module 7

Placeholder card on dashboard. Not clickable. Grey styling. Do NOT invent functionality.

**✅ PHASE 7 COMPLETE when:** All 6 management modules render with data.

---

## PHASE 8 — NOTIFICATIONS AND PROFILE

### Task 8.1 — Notifications

Three URLs (same template, different role prefix):
- `GET /student/notifications`
- `GET /driver/notifications`
- `GET /management/notifications`

Show list of notifications for logged-in user, sorted by newest first. Mark-as-read button.

### Task 8.2 — Profiles

Three URLs:
- `GET /student/profile`
- `GET /driver/profile`
- `GET /management/profile`

Show user info. Allow editing phone and email only.

**✅ PHASE 8 COMPLETE when:** Taskbar links work for all roles.

---

## PHASE 9 — ERROR PAGES AND POLISH

### Task 9.1 — Error Templates

| Template | Error | Illustration |
|---|---|---|
| `templates/error/404.html` | Not Found | `/assets/illustrations/error/404.svg` |
| `templates/error/500.html` | Server Error | `/assets/illustrations/error/500.svg` |
| `templates/error/access-denied.html` | Forbidden | `/assets/illustrations/error/access-denied.svg` |

### Task 9.2 — Empty States

When module has no data, show the correct empty state SVG from `assets/illustrations/empty/`.

### Task 9.3 — GlobalExceptionHandler

File: `common/exception/GlobalExceptionHandler.java` — `@ControllerAdvice` that catches exceptions and returns error templates.

**✅ PHASE 9 COMPLETE when:** Error pages display correctly, empty states show.

---

## PHASE 10 — SEED DATA AND FINAL VERIFICATION

### Task 10.1 — Full Seed Data

Create synthetic data for every table. See `docs/07-data/SEED_DATA.md` for exact values.

Minimum seed:
- 5 buses (MBU-001 through MBU-005)
- 5 routes with 4–6 stops each
- Assignments linking buses → routes → drivers → students
- 3 trips (ACTIVE, COMPLETED, SCHEDULED)
- 4 complaints (OPEN, IN_REVIEW, RESOLVED, ESCALATED)
- 3 maintenance records
- QR codes for both students
- 3 attendance records
- 4 emergency contacts
- 5 notifications

### Task 10.2 — Full Verification

Log in as each role. Verify every module. No broken pages. No empty states when seed data exists.

**✅ PHASE 10 COMPLETE when:** Full end-to-end manual walkthrough passes for all 3 roles.

---

## PHASE SUMMARY

| Phase | What | Files |
|---|---|---|
| 0 | Project init | pom.xml, application.properties |
| 1 | Entities + repos | ~40 Java files |
| 2 | Security | ~5 Java files |
| 3 | Homepage + login | ~6 HTML files |
| 4 | Dashboards | 3 HTML files |
| 5 | Student modules | ~10 Java + 5 HTML |
| 6 | Driver modules | ~10 Java + 5 HTML |
| 7 | Management modules | ~12 Java + 7 HTML |
| 8 | Notifications/profile | ~6 Java + 6 HTML |
| 9 | Error pages | 3 HTML + 1 Java |
| 10 | Seed data | 1 Java |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

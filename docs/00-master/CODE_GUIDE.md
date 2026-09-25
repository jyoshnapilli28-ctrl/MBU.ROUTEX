# MBU RouteX — CODE GUIDE

> **Status:** ACTIVE — Every line of code MUST follow this guide.  
> **Version:** 1.0  
> **Last Updated:** 2026-09-24  
> **Project:** MBU RouteX — TRACK · TRAVEL · CONNECT

---

## PURPOSE

This document defines EXACTLY how to write code for MBU RouteX. It contains:
1. Exact class patterns with copy-paste templates
2. Exact naming conventions
3. Exact HTML/CSS patterns
4. Exact URL patterns
5. Things that are FORBIDDEN

Any AI model implementing this project MUST follow this guide verbatim. When this guide conflicts with general best practices, THIS GUIDE WINS.

---

## SECTION 1: ABSOLUTE RULES

These rules are NON-NEGOTIABLE. Violating any of them means the code is WRONG.

```
RULE 01: Package root is com.mbu.routex — NEVER anything else.
RULE 02: Database is MySQL — NEVER H2, PostgreSQL, MongoDB, or anything else.
RULE 03: Template engine is Thymeleaf — NEVER JSP, React, Angular, or Vue.
RULE 04: CSS is pre-written in assets/styles/ — NEVER use Tailwind, Bootstrap, or Material.
RULE 05: Font is Inter — NEVER use Roboto, Arial, or any other font.
RULE 06: Roles are ROLE_STUDENT, ROLE_DRIVER, ROLE_MANAGEMENT — NEVER ROLE_FACULTY, ROLE_ADMIN, or ROLE_USER.
RULE 07: Entity field names MUST match database column names from DATABASE_SCHEMA.md.
RULE 08: Controller methods return String (Thymeleaf view name) — NEVER return ResponseEntity or JSON.
RULE 09: All controller classes use @Controller — NEVER @RestController.
RULE 10: All form submissions use POST with redirect — NEVER AJAX unless explicitly stated.
RULE 11: All dates use java.time.LocalDate or java.time.LocalDateTime — NEVER java.util.Date.
RULE 12: All IDs are Long — NEVER Integer, UUID, or String.
RULE 13: All enums are stored as STRING in database — use @Enumerated(EnumType.STRING).
RULE 14: Every page includes the taskbar fragment — NEVER omit it.
RULE 15: Every dashboard page includes the hero fragment — NEVER omit it.
RULE 16: Primary color is #577376 — NEVER change it.
RULE 17: Greeting text is EXACT:
          Student → "Hello MBUIans !!!"
          Driver  → "Hello Drivers !!!"
          Mgmt    → "Welcome Management !!!"
RULE 18: Module 7 is a placeholder — NEVER invent functionality for it.
RULE 19: All data is synthetic — NEVER use real names, real institutions, or real phone numbers.
RULE 20: Error pages use SVG illustrations from assets/illustrations/error/ — NEVER plain text errors.
```

---

## SECTION 2: PROJECT STRUCTURE

```
src/main/java/com/mbu/routex/
├── MbuRoutexApplication.java
├── config/
│   ├── SecurityConfig.java
│   ├── WebConfig.java
│   └── DataSeeder.java
├── common/
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java
│   │   ├── ResourceNotFoundException.java
│   │   └── AccessDeniedException.java
│   └── util/
│       ├── QRCodeUtil.java
│       └── DateUtil.java
├── auth/
│   ├── controller/
│   │   ├── HomeController.java
│   │   └── AuthController.java
│   ├── service/
│   │   └── CustomUserDetailsService.java
│   └── dto/
│       └── LoginRequest.java
├── user/
│   ├── entity/
│   │   ├── User.java
│   │   └── Role.java
│   └── repository/
│       └── UserRepository.java
├── student/
│   ├── entity/
│   │   ├── Student.java
│   │   └── StudentBusAssignment.java
│   ├── repository/
│   │   ├── StudentRepository.java
│   │   └── StudentBusAssignmentRepository.java
│   ├── controller/
│   │   ├── StudentBusController.java
│   │   ├── StudentTrackController.java
│   │   ├── StudentComplaintController.java
│   │   ├── StudentEmergencyController.java
│   │   └── StudentAttendanceController.java
│   ├── service/
│   │   ├── StudentBusService.java
│   │   ├── StudentTrackService.java
│   │   ├── StudentComplaintService.java
│   │   └── StudentAttendanceService.java
│   └── dto/
│       ├── StudentBusDto.java
│       ├── StudentTrackDto.java
│       └── ComplaintRequestDto.java
├── driver/
│   ├── entity/
│   │   ├── Driver.java
│   │   └── DriverAssignment.java
│   ├── repository/
│   │   ├── DriverRepository.java
│   │   └── DriverAssignmentRepository.java
│   ├── controller/
│   │   ├── DriverStatusController.java
│   │   ├── DriverServiceController.java
│   │   ├── DriverComplaintController.java
│   │   ├── DriverScheduleController.java
│   │   └── DriverEmergencyController.java
│   ├── service/
│   │   ├── DriverStatusService.java
│   │   ├── DriverServiceDetailsService.java
│   │   ├── DriverScheduleService.java
│   │   └── DriverComplaintService.java
│   └── dto/
│       ├── DriverStatusDto.java
│       └── DriverServiceDto.java
├── management/
│   ├── entity/
│   │   ├── ManagementUser.java
│   │   └── Report.java
│   ├── repository/
│   │   └── ManagementUserRepository.java
│   ├── controller/
│   │   ├── ManagementBusController.java
│   │   ├── ManagementTripController.java
│   │   ├── ManagementComplaintController.java
│   │   ├── ManagementMaintenanceController.java
│   │   ├── ManagementReportController.java
│   │   └── ManagementStudentController.java
│   ├── service/
│   │   ├── ManagementBusService.java
│   │   ├── ManagementTripService.java
│   │   ├── ManagementComplaintService.java
│   │   ├── ManagementMaintenanceService.java
│   │   ├── ManagementReportService.java
│   │   └── ManagementStudentService.java
│   └── dto/
│       ├── BusDetailDto.java
│       ├── TripSummaryDto.java
│       └── MaintenanceRecordDto.java
├── bus/
│   ├── entity/
│   │   ├── Bus.java
│   │   ├── BusStatus.java
│   │   └── BusAssignment.java
│   └── repository/
│       ├── BusRepository.java
│       └── BusAssignmentRepository.java
├── route/
│   ├── entity/
│   │   ├── Route.java
│   │   └── RouteStop.java
│   └── repository/
│       ├── RouteRepository.java
│       └── RouteStopRepository.java
├── trip/
│   ├── entity/
│   │   ├── Trip.java
│   │   └── TripStatus.java
│   └── repository/
│       └── TripRepository.java
├── complaint/
│   ├── entity/
│   │   ├── Complaint.java
│   │   ├── ComplaintStatus.java
│   │   ├── ComplaintStatusEnum.java
│   │   └── SubmitterType.java
│   └── repository/
│       ├── ComplaintRepository.java
│       └── ComplaintStatusRepository.java
├── maintenance/
│   ├── entity/
│   │   ├── MaintenanceRecord.java
│   │   └── MaintenanceStatus.java
│   └── repository/
│       └── MaintenanceRecordRepository.java
├── attendance/
│   ├── entity/
│   │   ├── Attendance.java
│   │   ├── AttendanceStatus.java
│   │   └── QRCode.java
│   └── repository/
│       ├── AttendanceRepository.java
│       └── QRCodeRepository.java
├── notification/
│   ├── entity/
│   │   └── Notification.java
│   └── repository/
│       └── NotificationRepository.java
└── emergency/
    ├── entity/
    │   ├── EmergencyContact.java
    │   └── EmergencyCategory.java
    └── repository/
        └── EmergencyContactRepository.java
```

---

## SECTION 3: CODE TEMPLATES

### 3.1 — Entity Template

EVERY entity follows this EXACT pattern:

```java
package com.mbu.routex.bus.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", nullable = false, unique = true, length = 50)
    private String busNumber;

    @Column(name = "registration", length = 100)
    private String registration;

    @Column(name = "college_serial", length = 50)
    private String collegeSerial;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BusStatus status = BusStatus.ACTIVE;

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
```

**Entity rules:**
- `@Entity` and `@Table(name = "...")` — table name matches DATABASE_SCHEMA.md
- `@Id` with `@GeneratedValue(strategy = GenerationType.IDENTITY)` — always IDENTITY for MySQL auto_increment
- Column names use `@Column(name = "snake_case")` — match DB exactly
- Enums use `@Enumerated(EnumType.STRING)` — ALWAYS STRING, never ORDINAL
- Timestamps use `LocalDateTime` with `@PrePersist` / `@PreUpdate`
- Use Lombok `@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder`
- If no Lombok: write getters/setters manually — DO NOT skip them

### 3.2 — Entity Relationship Template

```java
// Many-to-One (e.g., Student has one User)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

// One-to-Many (e.g., Route has many RouteStops)
@OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("stopOrder ASC")
private List<RouteStop> stops = new ArrayList<>();
```

**Relationship rules:**
- Always `FetchType.LAZY` — NEVER EAGER
- `@JoinColumn(name = "fk_column_name")` — column name matches DB FK column
- Inverse side uses `mappedBy`
- Do NOT use `@JsonIgnore` (we use Thymeleaf, not JSON)

### 3.3 — Repository Template

```java
package com.mbu.routex.bus.repository;

import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(String busNumber);
    List<Bus> findByStatus(BusStatus status);
}
```

**Repository rules:**
- Extend `JpaRepository<Entity, Long>` — ID type is ALWAYS Long
- Add `@Repository`
- Use Spring Data query methods (findByX) — avoid `@Query` unless necessary
- Return `Optional<T>` for single results, `List<T>` for multiple

### 3.4 — Service Template

```java
package com.mbu.routex.student.service;

import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.repository.StudentRepository;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentBusService {

    private final StudentRepository studentRepository;
    private final StudentBusAssignmentRepository assignmentRepository;

    public StudentBusDto getMyBus(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // ... business logic ...

        return dto;
    }
}
```

**Service rules:**
- `@Service` annotation — always
- `@Transactional(readOnly = true)` at class level for read-heavy services
- `@Transactional` (writable) on individual methods that modify data
- Constructor injection via `@RequiredArgsConstructor` (or manual constructor if no Lombok)
- Throw `ResourceNotFoundException` when entity not found — never return null
- Return DTOs to controllers — NEVER return entities directly to templates

### 3.5 — Controller Template

```java
package com.mbu.routex.student.controller;

import com.mbu.routex.student.service.StudentBusService;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentBusController {

    private final StudentBusService studentBusService;
    private final UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("greeting", "Hello MBUIans !!!");
        return "student/dashboard";
    }

    @GetMapping("/my-bus")
    public String myBus(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();
        model.addAttribute("busInfo", studentBusService.getMyBus(user.getId()));
        return "student/my-bus";
    }

    @PostMapping("/complaint")
    public String submitComplaint(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute ComplaintRequestDto request,
            RedirectAttributes redirectAttributes) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();
        studentComplaintService.submitComplaint(user.getId(), request);
        redirectAttributes.addFlashAttribute("success", "Complaint submitted successfully");
        return "redirect:/student/complaint";
    }
}
```

**Controller rules:**
- `@Controller` — NEVER `@RestController`
- `@RequestMapping("/role-prefix")` at class level
- Methods return `String` — the Thymeleaf template path (e.g., `"student/my-bus"`)
- Use `Model` to pass data to templates
- Use `@AuthenticationPrincipal UserDetails` to get the logged-in user
- POST methods use `redirect:` prefix (Post-Redirect-Get pattern)
- Flash attributes for success/error messages after POST
- NEVER return JSON, ResponseEntity, or Map

### 3.6 — DTO Template

```java
package com.mbu.routex.student.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentBusDto {
    private String busNumber;
    private String routeName;
    private String routeCode;
    private String driverName;
    private String driverPhone;
    private String busStatus;
    private List<String> stops;
}
```

**DTO rules:**
- Plain Java objects — no JPA annotations
- Use Lombok or manual getters/setters
- Field types are simple: String, Long, List, LocalDate, LocalDateTime
- NEVER expose entity objects directly in templates

### 3.7 — Exception Classes

```java
// ResourceNotFoundException.java
package com.mbu.routex.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

```java
// GlobalExceptionHandler.java
package com.mbu.routex.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("message", "An unexpected error occurred");
        return "error/500";
    }
}
```

---

## SECTION 4: THYMELEAF HTML TEMPLATES

### 4.1 — Page Structure Template

EVERY page follows this skeleton:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:replace="~{fragments/head :: head}"></head>
<body>

  <!-- Taskbar (on authenticated pages only) -->
  <div th:replace="~{fragments/taskbar :: taskbar('student')}"></div>

  <!-- Hero (on dashboard pages only) -->
  <div th:replace="~{fragments/hero :: hero('Hello MBUIans !!!')}"></div>

  <!-- Main Content -->
  <main class="container mt-6">
    <!-- page-specific content here -->
  </main>

  <!-- Footer (optional) -->
  <footer class="footer">
    <p>MBU RouteX — TRACK · TRAVEL · CONNECT</p>
  </footer>

</body>
</html>
```

### 4.2 — Dashboard Module Card HTML

```html
<div class="module-cards-grid">

  <a th:href="@{/student/my-bus}" class="module-card">
    <img th:src="@{/assets/icons/student/my-bus.svg}"
         alt="My Bus" class="module-card-icon" width="32" height="32">
    <h3 class="module-card-title">My Bus</h3>
  </a>

  <a th:href="@{/student/live-track}" class="module-card">
    <img th:src="@{/assets/icons/student/live-track.svg}"
         alt="Live Track" class="module-card-icon" width="32" height="32">
    <h3 class="module-card-title">Live Track</h3>
  </a>

  <!-- ... more cards ... -->

</div>
```

**HTML rules:**
- Use `th:href="@{/path}"` for ALL links — NEVER hardcode paths
- Use `th:src="@{/path}"` for ALL asset references
- Use `th:text="${variable}"` for dynamic text
- Use `th:each="item : ${list}"` for loops
- Use `th:if="${condition}"` for conditionals
- Use `sec:authorize="hasRole('STUDENT')"` for role-based visibility

### 4.3 — Data Table HTML

```html
<div class="table-responsive">
  <table class="data-table">
    <thead>
      <tr>
        <th>Bus Number</th>
        <th>Route</th>
        <th>Driver</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <tr th:each="bus : ${buses}">
        <td th:text="${bus.busNumber}">MBU-001</td>
        <td th:text="${bus.routeName}">Route A</td>
        <td th:text="${bus.driverName}">Driver Name</td>
        <td>
          <span class="status-badge"
                th:classappend="'status-' + ${bus.status.toLowerCase()}"
                th:text="${bus.status}">ACTIVE</span>
        </td>
      </tr>
    </tbody>
  </table>
</div>

<!-- Empty state when no data -->
<div th:if="${#lists.isEmpty(buses)}" class="empty-state">
  <img th:src="@{/assets/illustrations/empty/no-bus.svg}"
       alt="No buses found" width="120">
  <p>No bus data available</p>
</div>
```

### 4.4 — Form HTML

```html
<form th:action="@{/student/complaint}" method="POST" class="form-card">

  <div class="form-group">
    <label for="category" class="form-label">Category</label>
    <select id="category" name="category" class="form-select" required>
      <option value="">Select category</option>
      <option value="Delay">Delay</option>
      <option value="Driver behaviour">Driver behaviour</option>
      <option value="Bus condition">Bus condition</option>
      <option value="Overcrowding">Overcrowding</option>
      <option value="Route issue">Route issue</option>
      <option value="Cleanliness">Cleanliness</option>
      <option value="Other">Other</option>
    </select>
  </div>

  <div class="form-group">
    <label for="description" class="form-label">Description</label>
    <textarea id="description" name="description" class="form-textarea"
              rows="4" required placeholder="Describe your complaint..."></textarea>
  </div>

  <button type="submit" class="btn btn-primary">Submit Complaint</button>
</form>

<!-- Success message -->
<div th:if="${success}" class="alert alert-success" th:text="${success}"></div>
```

### 4.5 — Status Badge CSS Classes

```
status-active       → green background (#4a7c59)
status-on_route     → teal background (#577376)
status-delayed      → amber background (#b5860d)
status-stopped      → grey background (#9ca3af)
status-maintenance  → amber background (#b5860d)
status-completed    → muted teal (#698F92)
status-inactive     → grey (#9ca3af)

status-open         → grey
status-in_review    → amber
status-resolved     → green
status-escalated    → red (#b03030)

status-scheduled    → grey
status-cancelled    → red
```

These classes are ALREADY defined in `assets/styles/status.css`. Just apply them — do NOT redefine.

---

## SECTION 5: URL ROUTING MAP

### 5.1 — Public Routes

| Method | URL | Controller | Template |
|---|---|---|---|
| GET | `/` | `HomeController` | `index` |
| GET | `/login` | `AuthController` | `login/role-select` |
| GET | `/login/student` | `AuthController` | `login/student-login` |
| GET | `/login/driver` | `AuthController` | `login/driver-login` |
| GET | `/login/management` | `AuthController` | `login/management-login` |
| POST | `/login/process` | Spring Security | — |
| GET | `/logout` | Spring Security | redirect to `/` |

### 5.2 — Student Routes (ROLE_STUDENT only)

| Method | URL | Controller | Template |
|---|---|---|---|
| GET | `/student/dashboard` | `StudentBusController` | `student/dashboard` |
| GET | `/student/my-bus` | `StudentBusController` | `student/my-bus` |
| GET | `/student/live-track` | `StudentTrackController` | `student/live-track` |
| GET | `/student/complaint` | `StudentComplaintController` | `student/complaint` |
| POST | `/student/complaint` | `StudentComplaintController` | redirect to `student/complaint` |
| GET | `/student/emergency` | `StudentEmergencyController` | `student/emergency` |
| GET | `/student/attendance` | `StudentAttendanceController` | `student/attendance` |
| GET | `/student/attendance/qr-image` | `StudentAttendanceController` | — (returns PNG) |
| GET | `/student/notifications` | `StudentBusController` | `student/notifications` |
| GET | `/student/profile` | `StudentBusController` | `student/profile` |

### 5.3 — Driver Routes (ROLE_DRIVER only)

| Method | URL | Controller | Template |
|---|---|---|---|
| GET | `/driver/dashboard` | `DriverStatusController` | `driver/dashboard` |
| GET | `/driver/bus-status` | `DriverStatusController` | `driver/bus-status` |
| POST | `/driver/bus-status/update` | `DriverStatusController` | redirect to `driver/bus-status` |
| GET | `/driver/service-details` | `DriverServiceController` | `driver/service-details` |
| GET | `/driver/bus-complaints` | `DriverComplaintController` | `driver/bus-complaints` |
| POST | `/driver/bus-complaints` | `DriverComplaintController` | redirect to `driver/bus-complaints` |
| GET | `/driver/schedules` | `DriverScheduleController` | `driver/schedules` |
| GET | `/driver/emergency` | `DriverEmergencyController` | `driver/emergency` |
| GET | `/driver/notifications` | `DriverStatusController` | `driver/notifications` |
| GET | `/driver/profile` | `DriverStatusController` | `driver/profile` |

### 5.4 — Management Routes (ROLE_MANAGEMENT only)

| Method | URL | Controller | Template |
|---|---|---|---|
| GET | `/management/dashboard` | `ManagementBusController` | `management/dashboard` |
| GET | `/management/bus-details` | `ManagementBusController` | `management/bus-details` |
| GET | `/management/trips` | `ManagementTripController` | `management/trips` |
| GET | `/management/complaints` | `ManagementComplaintController` | `management/complaints` |
| POST | `/management/complaints/{id}/update` | `ManagementComplaintController` | redirect |
| GET | `/management/maintenance` | `ManagementMaintenanceController` | `management/maintenance` |
| POST | `/management/maintenance/add` | `ManagementMaintenanceController` | redirect |
| GET | `/management/reports` | `ManagementReportController` | `management/reports` |
| GET | `/management/students` | `ManagementStudentController` | `management/students` |
| GET | `/management/notifications` | `ManagementBusController` | `management/notifications` |
| GET | `/management/profile` | `ManagementBusController` | `management/profile` |

---

## SECTION 6: CSS CLASS REFERENCE

DO NOT write custom CSS. Use ONLY these pre-defined classes from `assets/styles/`:

### Layout Classes
| Class | Purpose |
|---|---|
| `.container` | Centered content container (max-width with padding) |
| `.module-cards-grid` | Grid layout for dashboard module cards |
| `.module-card` | Individual module card |
| `.module-card-icon` | Icon inside module card |
| `.module-card-title` | Title text in module card |

### Taskbar Classes
| Class | Purpose |
|---|---|
| `.taskbar` | Top navigation bar (dark teal background) |
| `.taskbar-logo` | Logo container |
| `.taskbar-nav` | Navigation links area |
| `.taskbar-link` | Individual nav link |
| `.taskbar-link.active` | Currently active nav link |

### Hero Classes
| Class | Purpose |
|---|---|
| `.hero` | Hero section wrapper |
| `.hero-image` | Background image |
| `.hero-overlay` | Semi-transparent teal overlay |
| `.hero-content` | Content positioned over overlay |
| `.hero-title` | Main heading in hero |

### Card / Form Classes
| Class | Purpose |
|---|---|
| `.card` | Generic card container |
| `.card-header` | Card header area |
| `.card-body` | Card body content |
| `.form-card` | Form styled as a card |
| `.form-group` | Form field wrapper |
| `.form-label` | Label text |
| `.form-input` | Text input |
| `.form-select` | Dropdown select |
| `.form-textarea` | Multi-line text input |
| `.btn` | Base button |
| `.btn-primary` | Primary action button (teal) |
| `.btn-secondary` | Secondary button |
| `.btn-danger` | Destructive action button |

### Table Classes
| Class | Purpose |
|---|---|
| `.data-table` | Styled data table |
| `.table-responsive` | Horizontal scroll wrapper for tables |

### Status Classes
| Class | Purpose |
|---|---|
| `.status-badge` | Colored status pill |
| `.status-active` | Green |
| `.status-delayed` | Amber |
| `.status-stopped` | Grey |
| `.status-open` | Grey |
| `.status-in_review` | Amber |
| `.status-resolved` | Green |
| `.status-escalated` | Red |

### Utility Classes
| Class | Purpose |
|---|---|
| `.mt-{n}` | Margin top (1–8) |
| `.mb-{n}` | Margin bottom |
| `.p-{n}` | Padding |
| `.text-center` | Center-aligned text |
| `.text-muted` | Muted grey text |
| `.alert` | Alert message container |
| `.alert-success` | Green success alert |
| `.alert-error` | Red error alert |
| `.empty-state` | Empty state container (centered, muted) |

---

## SECTION 7: SECURITY IMPLEMENTATION

### 7.1 — SecurityConfig.java (Complete Pattern)

```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login/**", "/assets/**").permitAll()
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/driver/**").hasRole("DRIVER")
                .requestMatchers("/management/**").hasRole("MANAGEMENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/error/access-denied")
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String redirectUrl = switch (role) {
                case "ROLE_STUDENT" -> "/student/dashboard";
                case "ROLE_DRIVER" -> "/driver/dashboard";
                case "ROLE_MANAGEMENT" -> "/management/dashboard";
                default -> "/";
            };
            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 7.2 — Getting Current User in Controllers

```java
// Option A: Use @AuthenticationPrincipal
@GetMapping("/my-bus")
public String myBus(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    // ... use user.getId() ...
}

// Option B: Use SecurityContextHolder (in service layer)
String username = SecurityContextHolder.getContext().getAuthentication().getName();
```

ALWAYS use Option A in controllers. Use Option B only in services when needed.

---

## SECTION 8: DATA PATTERNS

### 8.1 — Synthetic Demo Data Values

| Entity | Sample Values |
|---|---|
| Buses | MBU-001, MBU-002, MBU-003, MBU-004, MBU-005 |
| Routes | Indore City Route, Dewas Road Route, Ujjain Highway Route, Campus Internal, AB Road Express |
| Route Codes | RTE-001, RTE-002, RTE-003, RTE-004, RTE-005 |
| Stops | Main Gate, Library Circle, Engineering Block, Science Block, Hostel Area, Sports Complex |
| Student Names | Ravi Kumar, Priya Sharma, Amit Patel, Sneha Gupta |
| Driver Names | Suresh Patil, Ramesh Yadav |
| Management Names | Dr. Anita Desai |
| Departments | Computer Science, Electronics, Mechanical, Transport Management |
| Emergency Contacts | Transport Office: 0731-2XXXXXX, Campus Security: 0731-2XXXXXX |

> Use placeholder phone numbers (0731-2XXXXXX format). NEVER use real phone numbers.

### 8.2 — Status Lifecycle

**Bus Status:**
```
ACTIVE → ON_ROUTE → DELAYED (optional) → COMPLETED
ACTIVE → MAINTENANCE → ACTIVE
ACTIVE → STOPPED → ACTIVE
```

**Trip Status:**
```
SCHEDULED → ACTIVE → ON_ROUTE → DELAYED (optional) → COMPLETED
SCHEDULED → CANCELLED
```

**Complaint Status:**
```
OPEN → IN_REVIEW → RESOLVED
OPEN → IN_REVIEW → ESCALATED
```

**Maintenance Status:**
```
SCHEDULED → IN_PROGRESS → COMPLETED
SCHEDULED → OVERDUE
```

---

## SECTION 9: FORBIDDEN PATTERNS

These patterns will cause the implementation to FAIL the locked design. NEVER use them.

```
FORBIDDEN: @RestController (use @Controller)
FORBIDDEN: ResponseEntity return type (return String template name)
FORBIDDEN: JSON API responses (use Thymeleaf HTML)
FORBIDDEN: React, Angular, Vue (use Thymeleaf)
FORBIDDEN: Tailwind, Bootstrap, Material CSS (use assets/styles/)
FORBIDDEN: H2, PostgreSQL, MongoDB (use MySQL)
FORBIDDEN: ROLE_FACULTY, ROLE_ADMIN, ROLE_USER (only STUDENT, DRIVER, MANAGEMENT)
FORBIDDEN: FetchType.EAGER on relationships (always LAZY)
FORBIDDEN: java.util.Date (use java.time.LocalDateTime)
FORBIDDEN: Returning entity objects to Thymeleaf (use DTOs)
FORBIDDEN: Hardcoded asset paths (use th:src="@{/path}")
FORBIDDEN: Inline CSS styles (use the pre-defined CSS classes)
FORBIDDEN: Neon colors, gradients, glow effects, animations not in status.css
FORBIDDEN: Inventing Module 7 functionality
FORBIDDEN: Adding a Faculty/Admin role
FORBIDDEN: Changing the greeting text (Hello MBUIans !!! / Hello Drivers !!! / Welcome Management !!!)
FORBIDDEN: Changing the tagline (TRACK · TRAVEL · CONNECT)
FORBIDDEN: Changing the hero image or overlay color
FORBIDDEN: Using a different font than Inter
```

---

## SECTION 10: VERIFICATION CHECKLIST

After implementation, verify ALL of these pass:

```
[ ] Application starts on port 8080 without errors
[ ] MySQL database mbu_routex has all 15+ tables
[ ] Homepage shows hero with campus photo + teal overlay
[ ] Homepage shows 3 role selection cards (Student, Driver, Management)
[ ] Student login works with student1/password
[ ] Driver login works with driver1/password
[ ] Management login works with management1/password
[ ] Student dashboard shows "Hello MBUIans !!!" and 5 module cards
[ ] Driver dashboard shows "Hello Drivers !!!" and 5 module cards
[ ] Management dashboard shows "Welcome Management !!!" and 7 module cards
[ ] All 5 student modules load without error
[ ] All 5 driver modules load without error
[ ] All 6 management modules load without error
[ ] Student complaint form submits successfully
[ ] Driver bus complaint form submits successfully
[ ] Driver can update bus status
[ ] Management can update complaint status
[ ] Management can add maintenance records
[ ] QR code generates and displays for students
[ ] Notifications page loads for all roles
[ ] Profile page loads for all roles
[ ] 404 error page shows SVG illustration
[ ] 500 error page shows SVG illustration
[ ] Access denied page shows SVG illustration
[ ] Empty states show appropriate SVG illustrations
[ ] Mobile responsive layout works (cards stack vertically)
[ ] Taskbar shows on every authenticated page
[ ] Hero section shows on every dashboard
[ ] Logout redirects to homepage
[ ] Wrong role cannot access other role's URLs
[ ] No FACULTY role exists anywhere in the codebase
[ ] All colors match the locked palette (#577376 primary)
[ ] Font is Inter on all pages
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

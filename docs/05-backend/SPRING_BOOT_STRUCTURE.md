# MBU RouteX — Spring Boot Project Structure

> **Status:** PLANNED  
> **Version:** 1.0

---

## Root Package

```
com.mbu.routex
```

---

## Full Package Structure

```
src/
└── main/
    ├── java/
    │   └── com/mbu/routex/
    │       │
    │       ├── MbuRoutexApplication.java          // Spring Boot main class
    │       │
    │       ├── config/
    │       │   ├── SecurityConfig.java            // Spring Security configuration
    │       │   ├── WebConfig.java                 // CORS, MVC config
    │       │   └── ApplicationConfig.java         // General beans
    │       │
    │       ├── common/
    │       │   ├── exception/
    │       │   │   ├── GlobalExceptionHandler.java
    │       │   │   ├── ResourceNotFoundException.java
    │       │   │   ├── AccessDeniedException.java
    │       │   │   ├── ValidationException.java
    │       │   │   └── ErrorResponse.java
    │       │   └── util/
    │       │       ├── QRCodeUtil.java
    │       │       └── DateUtil.java
    │       │
    │       ├── auth/
    │       │   ├── controller/
    │       │   │   └── AuthController.java
    │       │   ├── service/
    │       │   │   ├── AuthService.java
    │       │   │   └── CustomUserDetailsService.java
    │       │   └── dto/
    │       │       ├── LoginRequest.java
    │       │       └── LoginResponse.java
    │       │
    │       ├── user/
    │       │   ├── entity/
    │       │   │   └── User.java
    │       │   └── repository/
    │       │       └── UserRepository.java
    │       │
    │       ├── student/
    │       │   ├── controller/
    │       │   │   ├── StudentBusController.java
    │       │   │   ├── StudentTrackController.java
    │       │   │   ├── StudentComplaintController.java
    │       │   │   ├── StudentEmergencyController.java
    │       │   │   └── StudentAttendanceController.java
    │       │   ├── service/
    │       │   │   ├── StudentBusService.java
    │       │   │   ├── StudentTrackService.java
    │       │   │   ├── StudentComplaintService.java
    │       │   │   └── StudentAttendanceService.java
    │       │   ├── repository/
    │       │   │   ├── StudentRepository.java
    │       │   │   └── StudentBusAssignmentRepository.java
    │       │   ├── entity/
    │       │   │   ├── Student.java
    │       │   │   └── StudentBusAssignment.java
    │       │   └── dto/
    │       │       ├── StudentBusDto.java
    │       │       ├── StudentTrackDto.java
    │       │       └── ComplaintRequestDto.java
    │       │
    │       ├── driver/
    │       │   ├── controller/
    │       │   │   ├── DriverStatusController.java
    │       │   │   ├── DriverServiceController.java
    │       │   │   ├── DriverComplaintController.java
    │       │   │   ├── DriverScheduleController.java
    │       │   │   └── DriverEmergencyController.java
    │       │   ├── service/
    │       │   │   ├── DriverStatusService.java
    │       │   │   ├── DriverServiceDetailsService.java
    │       │   │   ├── DriverScheduleService.java
    │       │   │   └── DriverComplaintService.java
    │       │   ├── repository/
    │       │   │   ├── DriverRepository.java
    │       │   │   └── DriverAssignmentRepository.java
    │       │   ├── entity/
    │       │   │   ├── Driver.java
    │       │   │   └── DriverAssignment.java
    │       │   └── dto/
    │       │       ├── DriverStatusDto.java
    │       │       └── DriverServiceDto.java
    │       │
    │       ├── management/
    │       │   ├── controller/
    │       │   │   ├── ManagementBusController.java
    │       │   │   ├── ManagementTripController.java
    │       │   │   ├── ManagementComplaintController.java
    │       │   │   ├── ManagementMaintenanceController.java
    │       │   │   ├── ManagementReportController.java
    │       │   │   └── ManagementStudentController.java
    │       │   ├── service/
    │       │   │   ├── ManagementBusService.java
    │       │   │   ├── ManagementTripService.java
    │       │   │   ├── ManagementComplaintService.java
    │       │   │   ├── ManagementMaintenanceService.java
    │       │   │   ├── ManagementReportService.java
    │       │   │   └── ManagementStudentService.java
    │       │   ├── repository/
    │       │   │   └── ManagementUserRepository.java
    │       │   ├── entity/
    │       │   │   └── ManagementUser.java
    │       │   └── dto/
    │       │       ├── BusDetailDto.java
    │       │       ├── TripSummaryDto.java
    │       │       └── MaintenanceRecordDto.java
    │       │
    │       ├── bus/
    │       │   ├── entity/Bus.java
    │       │   └── repository/BusRepository.java
    │       │
    │       ├── route/
    │       │   ├── entity/
    │       │   │   ├── Route.java
    │       │   │   └── RouteStop.java
    │       │   └── repository/
    │       │       ├── RouteRepository.java
    │       │       └── RouteStopRepository.java
    │       │
    │       ├── trip/
    │       │   ├── entity/Trip.java
    │       │   └── repository/TripRepository.java
    │       │
    │       ├── complaint/
    │       │   ├── entity/
    │       │   │   ├── Complaint.java
    │       │   │   └── ComplaintStatus.java
    │       │   └── repository/
    │       │       ├── ComplaintRepository.java
    │       │       └── ComplaintStatusRepository.java
    │       │
    │       ├── maintenance/
    │       │   ├── entity/MaintenanceRecord.java
    │       │   └── repository/MaintenanceRecordRepository.java
    │       │
    │       ├── attendance/
    │       │   ├── entity/
    │       │   │   ├── Attendance.java
    │       │   │   └── QRCode.java
    │       │   └── repository/
    │       │       ├── AttendanceRepository.java
    │       │       └── QRCodeRepository.java
    │       │
    │       ├── notification/
    │       │   ├── entity/Notification.java
    │       │   └── repository/NotificationRepository.java
    │       │
    │       └── emergency/
    │           ├── entity/EmergencyContact.java
    │           └── repository/EmergencyContactRepository.java
    │
    └── resources/
        ├── application.properties
        ├── application-dev.properties
        ├── application-prod.properties
        ├── static/
        │   ├── css/
        │   │   └── style.css
        │   ├── js/
        │   │   └── app.js
        │   └── assets/
        │       ├── logo/          // Locked MBU RouteX Logo
        │       └── images/        // Campus hero photograph
        └── templates/             // Thymeleaf templates (if used)
            ├── index.html
            ├── login/
            │   ├── student-login.html
            │   ├── driver-login.html
            │   └── management-login.html
            ├── student/
            │   └── dashboard.html
            ├── driver/
            │   └── dashboard.html
            └── management/
                └── dashboard.html
```

---

## application.properties (Development Template)

```properties
# Application
spring.application.name=MBU RouteX

# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/mbu_routex?useSSL=false&serverTimezone=Asia/Kolkata
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.com.mbu.routex=INFO
logging.level.org.springframework.security=WARN
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

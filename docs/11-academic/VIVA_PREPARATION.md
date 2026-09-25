# MBU RouteX — Viva Preparation

> **Status:** PLANNED  
> **Version:** 1.0

---

## Expected Viva Questions and Answers

### Project Overview

**Q: What is MBU RouteX?**  
A: MBU RouteX is a Java/Spring Boot-based university transportation management web application for Mohan Babu University. It provides role-based dashboards for students, drivers, and management to manage bus information, live tracking, complaints, QR attendance, and transport operations.

**Q: What problem does it solve?**  
A: It replaces manual, disconnected transportation management — paper attendance, phone complaints, no real-time visibility — with a centralized digital platform.

**Q: What are the three user roles?**  
A: ROLE_STUDENT, ROLE_DRIVER, and ROLE_MANAGEMENT. Each has its own login, dashboard, and set of features.

---

### Technology Stack

**Q: Why did you choose Spring Boot?**  
A: Spring Boot is a production-grade Java framework with built-in support for REST APIs, Spring Security for authentication/authorization, Spring Data JPA for database operations, and a large ecosystem — making it well-suited for this kind of institutional web application.

**Q: What database does the system use and why?**  
A: MySQL. It is a widely-used, reliable relational database that integrates well with Spring Data JPA and Hibernate, and is appropriate for the structured, relational data in this system (buses, routes, students, trips).

**Q: What is the architecture pattern?**  
A: Modular Monolith — a single deployable application with clear internal module separation by domain (student, driver, management, bus, route, etc.) and layered architecture (Controller → Service → Repository → Entity).

---

### Security

**Q: How are passwords stored?**  
A: Using BCrypt hashing via Spring Security's BCryptPasswordEncoder. Plain text passwords are never stored.

**Q: How is role-based access enforced?**  
A: Through Spring Security's HTTP security configuration. Each URL pattern is mapped to a required role. Cross-role access returns HTTP 403. Unauthenticated access returns 401 or redirects to login.

**Q: How is SQL injection prevented?**  
A: Spring Data JPA uses parameterized queries by default. JPQL queries use named parameters, never string concatenation.

**Q: How is XSS prevented?**  
A: Thymeleaf auto-escapes output by default when using `th:text`. All output is HTML-encoded before rendering.

---

### Database

**Q: How many database entities does the system have?**  
A: 19 entities including User, Student, Driver, ManagementUser, Bus, Route, RouteStop, Trip, BusAssignment, StudentBusAssignment, DriverAssignment, Complaint, ComplaintStatus, MaintenanceRecord, Attendance, QRCode, Notification, EmergencyContact, and Report.

**Q: What is the relationship between Student and Bus?**  
A: Many-to-many via the `StudentBusAssignment` junction table, which also records the boarding stop and assignment period.

**Q: How does QR attendance work?**  
A: Each student has a unique QR code token stored in the `qr_codes` table. When scanned, the system validates the token, identifies the student and active trip, and records an attendance entry in the `attendance` table.

---

### Features

**Q: What are the 5 student dashboard modules?**  
A: My Bus, Live Track, Complaint, Emergency, and Attendance – My QR.

**Q: What are the 5 driver dashboard modules?**  
A: Bus Status, Service Details, Bus Complaints, Schedules, and Emergency.

**Q: How many management modules are defined?**  
A: Six are currently defined: Bus Details, Trip Management, Complaints, Maintenance, Reports, and Student Overview. A seventh module is reserved and TO BE DEFINED by the project owner.

**Q: What complaint categories exist for students?**  
A: Delay, Driver Behaviour, Bus Condition, Overcrowding, Route Issue, Cleanliness, and Other.

**Q: What complaint categories exist for drivers?**  
A: Tyre Problem, Brake Problem, Engine Problem, Mechanical Issue, Cleanliness, Bus Damage, and Other.

---

### Design

**Q: What was the design philosophy?**  
A: Professional, clean, and university-oriented. The interface uses a restrained teal/green/grey/white color palette, the MBU campus hero photograph, and a consistent card-based dashboard design. The goal was to look like a real university transportation system built by a skilled human team.

**Q: Why was this color palette chosen?**  
A: It was selected and locked by the project owner to reflect the university's professional institutional identity — calm, trustworthy, and consistent.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

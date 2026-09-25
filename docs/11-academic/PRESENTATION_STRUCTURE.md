# MBU RouteX — Presentation Structure

> **Status:** PLANNED  
> **Version:** 1.0

---

## Slide Deck Outline

### Slide 1 — Title Slide
- MBU RouteX
- TRACK · TRAVEL · CONNECT
- Locked MBU RouteX Logo
- Team names / Roll numbers
- Institution: Mohan Babu University
- Date

---

### Slide 2 — Problem Statement
- University transportation management challenges at MBU
- Paper-based attendance
- No real-time visibility
- Disconnected complaint process
- No centralized emergency access

---

### Slide 3 — Solution Overview
- MBU RouteX — centralized digital transport platform
- Three user roles: Student · Driver · Management
- Key modules: Live Track, QR Attendance, Complaints, Maintenance, Trip Management
- Tagline: TRACK · TRAVEL · CONNECT

---

### Slide 4 — Technology Stack
- Backend: Java, Spring Boot, Spring Security, Spring Data JPA, Hibernate, Maven
- Database: MySQL
- Frontend: HTML, CSS, JavaScript
- Architecture: Modular Monolith
- Version Control: Git / GitHub

---

### Slide 5 — System Architecture
- Mermaid/diagram: Browser → Spring Boot → MySQL
- Layer overview: Controller → Service → Repository → Entity
- Spring Security: Authentication + RBAC

---

### Slide 6 — Homepage
- Screenshot / mockup of public homepage
- Campus hero photograph with teal overlay
- DREAM · BELIEVE · ACHIEVE
- Three role cards: Student · Driver · Management

---

### Slide 7 — Student Dashboard
- Screenshot / mockup
- Greeting: Hello MBUIans !!!
- 5 modules: My Bus, Live Track, Complaint, Emergency, Attendance – My QR

---

### Slide 8 — Driver Dashboard
- Screenshot / mockup
- Greeting: Hello Drivers !!!
- 5 modules: Bus Status, Service Details, Bus Complaints, Schedules, Emergency

---

### Slide 9 — Management Dashboard
- Screenshot / mockup
- Greeting: Welcome Management !!!
- 6 defined modules + 1 TO BE DEFINED

---

### Slide 10 — Database Design
- Simplified ER diagram
- Key entities: User, Student, Driver, Bus, Route, Trip, Complaint, Attendance, QRCode
- Highlight key relationships

---

### Slide 11 — Security
- Spring Security — role-based access control
- BCrypt password hashing
- RBAC permission matrix
- SQL injection prevention
- XSS protection

---

### Slide 12 — QR Attendance System
- Flow diagram: Student shows QR → Scan → Validate → Record attendance
- Digital replacement for paper roll call

---

### Slide 13 — Complaint System
- Complaint lifecycle: OPEN → IN REVIEW → RESOLVED / ESCALATED
- Sources: Students (service complaints) + Drivers (bus issue reports)
- Management: view, filter, respond, resolve

---

### Slide 14 — Testing
- Unit Testing (JUnit 5 + Mockito)
- API Testing (MockMvc)
- Security Testing (role access verification)
- Manual acceptance testing

---

### Slide 15 — Conclusion & Future Scope
- What was achieved
- Future scope: GPS integration, mobile app, SMS notifications, parent portal
- Thank you

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

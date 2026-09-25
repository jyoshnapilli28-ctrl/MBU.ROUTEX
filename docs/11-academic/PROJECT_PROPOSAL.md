# MBU RouteX — Project Proposal

> **Status:** COMPLETED  
> **Document Type:** Academic Project Proposal

---

## Project Title

**MBU RouteX — University Transportation Management and Tracking Web Application**

---

## Tagline

TRACK · TRAVEL · CONNECT

---

## Introduction

University transportation management at large institutions like Mohan Babu University involves coordinating multiple buses, dozens of routes, hundreds of students, and multiple drivers on a daily basis. Current manual methods — paper attendance, verbal complaint reporting, no real-time visibility — create significant operational inefficiencies and student dissatisfaction.

This project proposes the development of **MBU RouteX**, a centralized web application to digitize and streamline this process.

---

## Problem Statement

1. Students have no digital access to their bus assignment, schedule, or real-time location.
2. Attendance is recorded manually — time-consuming and error-prone.
3. Complaints about bus conditions or delays have no centralized reporting channel.
4. Management has no real-time overview of fleet operations.
5. Emergency contacts are not centrally accessible to students or drivers.
6. Maintenance records are maintained manually without a structured system.

---

## Proposed Solution

A three-role web application built on Spring Boot and MySQL:

| Role | Key Capabilities |
|---|---|
| STUDENT | View assigned bus, live tracking, complaint submission, QR attendance, emergency contacts |
| DRIVER | Bus status management, service details, complaint reporting, trip schedule, emergency contacts |
| MANAGEMENT | Fleet oversight, trip monitoring, complaint management, maintenance tracking, reports, student overview |

---

## Objectives

1. Provide students with digital access to their transportation information
2. Replace paper attendance with a QR code-based digital system
3. Create a centralized complaint channel for students and drivers
4. Give management real-time operational visibility
5. Build a maintainable, role-secured web application on Java/Spring Boot

---

## Technology Stack

| Component | Technology |
|---|---|
| Backend | Java 17, Spring Boot, Spring Security, Spring Data JPA |
| Database | MySQL 8.0 |
| ORM | Hibernate |
| Frontend | HTML, CSS (custom), JavaScript |
| Security | Spring Security, BCrypt |
| Build | Maven |
| Version Control | Git / GitHub |

---

## Architecture

Modular Monolith — Spring Boot with layered architecture (Controller → Service → Repository → Entity). Role-based access control enforced by Spring Security.

---

## Expected Deliverables

1. Working MBU RouteX web application
2. Source code on GitHub with Git history
3. Complete project documentation (`docs/` directory)
4. Database schema (MySQL)
5. Academic report
6. Presentation slides
7. Viva

---

## Scope

**In Scope:**
- Three-role system: STUDENT, DRIVER, MANAGEMENT
- Student portal: My Bus, Live Track, Complaints, Emergency, QR Attendance
- Driver portal: Bus Status, Service Details, Bus Complaints, Schedules, Emergency
- Management portal: Bus Details, Trip Management, Complaints, Maintenance, Reports, Student Overview
- Secure authentication and RBAC
- Responsive web interface

**Out of Scope (for this version):**
- Native mobile application
- SMS/push notifications
- External GPS device integration (Live Track is planned; GPS source TBD)
- Parent portal
- Payment processing

---

## Conclusion

MBU RouteX is a practical, implementable solution to real transportation management challenges at Mohan Babu University. It leverages industry-standard Java technologies and delivers a professional, role-specific user experience for students, drivers, and management.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

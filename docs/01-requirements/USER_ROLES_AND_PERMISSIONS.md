# MBU RouteX — User Roles and Permissions

> **Status:** LOCKED  
> **Version:** 1.0

---

## Role Overview

MBU RouteX defines exactly three authenticated roles:

| Role Constant | Display Name | Portal URL | Dashboard Greeting |
|---|---|---|---|
| `ROLE_STUDENT` | Student | `/student/dashboard` | Hello MBUIans !!! |
| `ROLE_DRIVER` | Driver | `/driver/dashboard` | Hello Drivers !!! |
| `ROLE_MANAGEMENT` | Management | `/management/dashboard` | Welcome Management !!! |

> IMPORTANT: The role FACULTY has been superseded. The correct operational role is DRIVER. This applies to all code, configuration, database entries, and documentation.

---

## Authentication Flow

```
Public Homepage
     |
     |-- Student Card --> /login/student --> ROLE_STUDENT --> /student/dashboard
     |-- Driver Card  --> /login/driver  --> ROLE_DRIVER  --> /driver/dashboard
     |-- Management Card -> /login/management -> ROLE_MANAGEMENT -> /management/dashboard
```

---

## Permission Matrix

| Feature / Module | ROLE_STUDENT | ROLE_DRIVER | ROLE_MANAGEMENT |
|---|---|---|---|
| **Public Homepage** | VIEW | VIEW | VIEW |
| **Student Login** | YES | NO | NO |
| **Driver Login** | NO | YES | NO |
| **Management Login** | NO | NO | YES |
| **Student Dashboard** | YES | NO | NO |
| **Driver Dashboard** | NO | YES | NO |
| **Management Dashboard** | NO | NO | YES |
| | | | |
| **My Bus** | VIEW | NO | NO |
| **Live Track** | VIEW | NO | NO |
| **Complaint (Student)** | SUBMIT / VIEW OWN | NO | NO |
| **Emergency (Student)** | VIEW | NO | NO |
| **Attendance – My QR** | VIEW OWN | NO | NO |
| | | | |
| **Bus Status** | NO | VIEW / UPDATE OWN | NO |
| **Service Details** | NO | VIEW OWN | NO |
| **Bus Complaints** | NO | SUBMIT / VIEW OWN | NO |
| **Schedules** | NO | VIEW OWN | NO |
| **Emergency (Driver)** | NO | VIEW | NO |
| | | | |
| **Bus Details** | NO | NO | VIEW / MANAGE |
| **Trip Management** | NO | NO | VIEW / MANAGE |
| **Complaints (All)** | NO | NO | VIEW / MANAGE / RESOLVE |
| **Maintenance** | NO | NO | VIEW / MANAGE |
| **Reports** | NO | NO | VIEW / GENERATE |
| **Student Overview** | NO | NO | VIEW |
| **Module 7** | TO BE DEFINED | TO BE DEFINED | TO BE DEFINED |

---

## Authorization Rules

### ROLE_STUDENT
- Can only access `/student/**` endpoints
- Can only view their own bus assignment, QR, complaints, and attendance
- Cannot view other students' data
- Cannot access Driver or Management features

### ROLE_DRIVER
- Can only access `/driver/**` endpoints
- Can only view their own assigned bus, service, schedule
- Cannot access Student or Management features
- Student list visibility: limited by authorization rules (TO BE DEFINED in detail)

### ROLE_MANAGEMENT
- Can access `/management/**` endpoints
- Can view all fleet, complaints, trips, maintenance, reports, and student data
- Cannot perform student-level or driver-level personal operations
- Has the broadest data visibility of all roles

---

## Access Denied Behavior

| Scenario | HTTP Code | User Experience |
|---|---|---|
| Unauthenticated user accesses protected page | 401 | Redirect to login |
| Authenticated user accesses wrong role's page | 403 | Access denied page |
| Invalid credentials at login | 401 | Error message on login form |
| Session expired | 401 | Redirect to login with message |

---

## Spring Security Role Configuration

```java
// Conceptual Spring Security configuration
.requestMatchers("/", "/login/**", "/css/**", "/js/**", "/assets/**").permitAll()
.requestMatchers("/student/**").hasRole("STUDENT")
.requestMatchers("/driver/**").hasRole("DRIVER")
.requestMatchers("/management/**").hasRole("MANAGEMENT")
.anyRequest().authenticated()
```

See `05-backend/SECURITY.md` for full Spring Security implementation documentation.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

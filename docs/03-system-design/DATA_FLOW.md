# MBU RouteX — Data Flow

> **Status:** PLANNED  
> **Version:** 1.0

---

## Overview

This document describes the flow of data through the MBU RouteX system — from user input, through the Spring Boot layers, to the database, and back to the client.

---

## Data Flow Diagram — Overall System

```mermaid
flowchart TD
    Browser["Browser\nHTML Form / JS Fetch"]
    Security["Spring Security\nAuth Filter"]
    Controller["Controller\nHTTP Handler"]
    Service["Service\nBusiness Logic"]
    Mapper["Mapper\nEntity ↔ DTO"]
    Repository["Repository\nSpring Data JPA"]
    DB[(MySQL Database)]

    Browser -->|HTTP Request + Session Cookie| Security
    Security -->|Authenticated Request| Controller
    Controller -->|RequestDTO| Service
    Service -->|Entity Query| Repository
    Repository -->|JPA Query| DB
    DB -->|ResultSet| Repository
    Repository -->|Entity| Service
    Service -->|ResponseDTO via Mapper| Controller
    Controller -->|HTTP Response JSON| Browser
```

---

## Data Flow — Complaint Submission (Student)

```mermaid
sequenceDiagram
    participant Student as Student Browser
    participant Security as Spring Security
    participant Controller as ComplaintController
    participant Service as ComplaintService
    participant Repo as ComplaintRepository
    participant DB as MySQL

    Student->>Security: POST /api/student/complaints {category, description}
    Security->>Security: Verify session — ROLE_STUDENT
    Security->>Controller: Authorized request
    Controller->>Controller: Validate @Valid ComplaintRequestDto
    Controller->>Service: submitComplaint(dto, studentId)
    Service->>Service: Build Complaint entity, set status=OPEN
    Service->>Repo: save(complaint)
    Repo->>DB: INSERT INTO complaints
    DB-->>Repo: Saved entity with ID
    Repo-->>Service: Saved entity
    Service-->>Controller: ComplaintResponseDto
    Controller-->>Student: HTTP 201 Created {id, status: OPEN}
```

---

## Data Flow — Student Views My Bus

```mermaid
sequenceDiagram
    participant Student as Student Browser
    participant Controller as StudentBusController
    participant Service as StudentBusService
    participant Repo as StudentBusAssignmentRepository
    participant DB as MySQL

    Student->>Controller: GET /api/student/bus
    Controller->>Service: getStudentBusInfo(studentId)
    Service->>Repo: findActiveAssignmentByStudentId(studentId)
    Repo->>DB: SELECT with JOIN buses, routes, drivers
    DB-->>Repo: Result
    Repo-->>Service: StudentBusAssignment entity
    Service-->>Controller: StudentBusDto
    Controller-->>Student: HTTP 200 {busNumber, route, driver, ...}
```

---

## Data Flow — Management Updates Complaint Status

```mermaid
sequenceDiagram
    participant Mgmt as Management Browser
    participant Controller as ManagementComplaintController
    participant Service as ManagementComplaintService
    participant Repo as ComplaintRepository
    participant StatusRepo as ComplaintStatusRepository
    participant DB as MySQL

    Mgmt->>Controller: PUT /api/management/complaints/{id}/status {status: RESOLVED}
    Controller->>Service: updateComplaintStatus(id, RESOLVED, managerId)
    Service->>Repo: findById(id)
    Repo->>DB: SELECT from complaints
    DB-->>Repo: Complaint entity
    Repo-->>Service: Complaint
    Service->>Repo: update complaint.currentStatus = RESOLVED
    Service->>StatusRepo: save(new ComplaintStatus entry)
    DB-->>Service: Updated
    Service-->>Controller: Success
    Controller-->>Mgmt: HTTP 200 {id, status: RESOLVED}
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

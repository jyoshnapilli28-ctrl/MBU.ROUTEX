# MBU RouteX — System Architecture

> **Status:** PLANNED  
> **Version:** 1.0

---

## Architecture Pattern

MBU RouteX uses a **Modular Monolith** architecture for the initial release.

This means:
- A single deployable application
- Internal module separation by domain
- Clear layer boundaries: Controller → Service → Repository → Entity
- Supports future extraction of modules into services if needed

---

## High-Level Architecture

```mermaid
graph TD
    Browser["Browser\nHTML / CSS / JavaScript"]
    
    subgraph SpringBoot["Spring Boot Application"]
        Controller["Controller Layer\n(REST Controllers)"]
        Service["Service Layer\n(Business Logic)"]
        Repository["Repository Layer\n(Spring Data JPA)"]
        Security["Spring Security\n(Auth + RBAC)"]
        Entity["Entity Layer\n(JPA / Hibernate)"]
    end
    
    MySQL["MySQL Database"]
    
    Browser -->|HTTP Requests| Security
    Security --> Controller
    Controller --> Service
    Service --> Repository
    Repository --> Entity
    Entity -->|JPA / Hibernate| MySQL
    Service -->|DTOs| Controller
    Controller -->|JSON Response| Browser
```

---

## Application Layers

| Layer | Responsibility |
|---|---|
| **Controller** | Handle HTTP requests; map URLs to service calls; return responses |
| **Service** | Business logic; validation; transaction management; data transformation |
| **Repository** | Database access via Spring Data JPA; queries |
| **Entity** | JPA-mapped domain objects corresponding to database tables |
| **DTO** | Data transfer objects for API request/response payloads |
| **Mapper** | Map between Entity and DTO (manual or MapStruct) |
| **Security** | Spring Security: authentication, authorization, session management |
| **Exception** | Global exception handling; consistent error responses |
| **Configuration** | Application configuration, Spring Security config, CORS, etc. |

---

## Module Structure

```
com.mbu.routex
├── auth/
│   ├── controller/
│   ├── service/
│   ├── dto/
│   └── security/
├── student/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── driver/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── management/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── bus/
├── route/
├── trip/
├── complaint/
├── maintenance/
├── attendance/
├── emergency/
├── notification/
├── report/
├── common/
│   ├── exception/
│   ├── config/
│   └── util/
└── MbuRoutexApplication.java
```

---

## Request Flow

```mermaid
sequenceDiagram
    participant Browser
    participant Security as Spring Security
    participant Controller
    participant Service
    participant Repository
    participant DB as MySQL

    Browser->>Security: HTTP Request
    Security->>Security: Authenticate & Authorize
    Security->>Controller: Authorized Request
    Controller->>Service: Call Service Method
    Service->>Repository: Data Query
    Repository->>DB: SQL (JPA)
    DB-->>Repository: Result Set
    Repository-->>Service: Entity
    Service-->>Controller: DTO
    Controller-->>Browser: HTTP Response (JSON)
```

---

## Authentication Flow

```mermaid
flowchart TD
    A[User visits Homepage] --> B[Selects Role Card]
    B --> C{Role?}
    C -->|Student| D[POST /login/student]
    C -->|Driver| E[POST /login/driver]
    C -->|Management| F[POST /login/management]
    D --> G[Spring Security authenticates]
    E --> G
    F --> G
    G --> H{Valid?}
    H -->|No| I[Return to login with error]
    H -->|Yes| J{Check Role}
    J -->|ROLE_STUDENT| K[Redirect /student/dashboard]
    J -->|ROLE_DRIVER| L[Redirect /driver/dashboard]
    J -->|ROLE_MANAGEMENT| M[Redirect /management/dashboard]
```

---

## Security Architecture

- **Authentication:** Spring Security form-based login (session) or JWT — TO BE DEFINED
- **Password storage:** BCrypt hashing
- **Authorization:** Role-based — `@PreAuthorize` or `HttpSecurity` config
- **Session:** Server-side session (initial); JWT optional for future
- **CSRF:** Enabled for form-based; may be disabled for REST if using JWT
- **HTTPS:** Required in production

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

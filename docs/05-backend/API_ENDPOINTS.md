# MBU RouteX — API Endpoints

> **Status:** PLANNED  
> **Version:** 1.0  
> **Note:** These are conceptual endpoint structures and may be refined during implementation.

---

## API Conventions

| Convention | Standard |
|---|---|
| Base path | `/api` |
| Response format | JSON |
| Auth requirement | All `/api/**` endpoints require authentication unless noted |
| HTTP verbs | GET for retrieval, POST for creation, PUT/PATCH for update, DELETE for removal |
| Error format | Standard JSON error response (see Backend Architecture) |

---

## Authentication Endpoints

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/login` | Public | Form login (Spring Security) |
| POST | `/logout` | Authenticated | Logout and clear session |
| GET | `/` | Public | Public homepage |
| GET | `/login/student` | Public | Student login page |
| GET | `/login/driver` | Public | Driver login page |
| GET | `/login/management` | Public | Management login page |

---

## Student API

**Base path:** `/api/student`  
**Required role:** `ROLE_STUDENT`

### My Bus

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/student/bus` | Get student's assigned bus information |
| GET | `/api/student/bus/status` | Get current status of assigned bus |

### Live Tracking

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/student/tracking` | Get current location data for assigned bus |
| GET | `/api/student/tracking/eta` | Get estimated arrival time |

### Complaints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/student/complaints` | Get all complaints submitted by student |
| GET | `/api/student/complaints/{id}` | Get specific complaint by ID |
| POST | `/api/student/complaints` | Submit a new complaint |

### Emergency

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/student/emergency` | Get emergency contacts for student view |

### Attendance

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/student/attendance` | Get student's attendance history |
| GET | `/api/student/attendance/qr` | Get student's QR code data |
| GET | `/api/student/attendance/status` | Get current scan/check-in status |

---

## Driver API

**Base path:** `/api/driver`  
**Required role:** `ROLE_DRIVER`

### Bus Status

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/driver/status` | Get current bus status for driver's assigned bus |
| PUT | `/api/driver/status` | Update bus operational status |

### Service Details

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/driver/service` | Get driver's current service details |
| GET | `/api/driver/service/route` | Get route details for current service |
| GET | `/api/driver/service/stops` | Get stop list for current route |

### Bus Complaints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/driver/complaints` | Get all bus issues reported by driver |
| GET | `/api/driver/complaints/{id}` | Get specific complaint |
| POST | `/api/driver/complaints` | Report a new bus issue |

### Schedules

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/driver/schedules` | Get driver's full schedule |
| GET | `/api/driver/schedules/today` | Get today's trips |
| GET | `/api/driver/schedules/upcoming` | Get upcoming assigned trips |

### Emergency

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/driver/emergency` | Get emergency contacts for driver view |

---

## Management API

**Base path:** `/api/management`  
**Required role:** `ROLE_MANAGEMENT`

### Bus Details

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/buses` | Get all buses in fleet |
| GET | `/api/management/buses/{id}` | Get specific bus details |
| GET | `/api/management/buses/{id}/students` | Get students assigned to a bus |
| POST | `/api/management/buses` | Add a new bus |
| PUT | `/api/management/buses/{id}` | Update bus details |

### Trip Management

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/trips` | Get all trips |
| GET | `/api/management/trips/active` | Get all currently active trips |
| GET | `/api/management/trips/{id}` | Get specific trip details |
| POST | `/api/management/trips` | Create a new trip |
| PUT | `/api/management/trips/{id}/status` | Update trip status |

### Complaints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/complaints` | Get all complaints (filterable) |
| GET | `/api/management/complaints/{id}` | Get specific complaint |
| PUT | `/api/management/complaints/{id}/status` | Update complaint status |
| PUT | `/api/management/complaints/{id}/response` | Add management response |

### Maintenance

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/maintenance` | Get all maintenance records |
| GET | `/api/management/maintenance/upcoming` | Get upcoming maintenance |
| GET | `/api/management/maintenance/{busId}` | Get maintenance for specific bus |
| POST | `/api/management/maintenance` | Add maintenance record |
| PUT | `/api/management/maintenance/{id}` | Update maintenance record |

### Reports

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/reports` | Get available reports |
| GET | `/api/management/reports/{type}` | Get specific report by type |
| POST | `/api/management/reports/generate` | Generate a new report |

### Student Overview

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/management/students` | Get all students with bus assignment |
| GET | `/api/management/students/by-bus/{busId}` | Get students assigned to a specific bus |
| GET | `/api/management/students/by-route/{routeId}` | Get students on a specific route |
| GET | `/api/management/students/attendance` | Get attendance overview |

---

## Sample Request / Response

### POST /api/student/complaints

**Request:**
```json
{
  "category": "DELAY",
  "description": "Bus was 30 minutes late today"
}
```

**Response (201 Created):**
```json
{
  "id": 42,
  "category": "DELAY",
  "description": "Bus was 30 minutes late today",
  "status": "OPEN",
  "submittedAt": "2026-09-24T08:30:00"
}
```

### GET /api/student/bus

**Response (200 OK):**
```json
{
  "busNumber": "DEMO-01",
  "routeName": "Route A — DEMO",
  "driverName": "DEMO DATA",
  "schedule": "08:00 AM",
  "assignedStop": "Stop 3 — DEMO",
  "status": "ACTIVE"
}
```

> All sample values are DEMO DATA. Real values will come from the database.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

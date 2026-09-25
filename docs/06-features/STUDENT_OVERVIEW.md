# MBU RouteX — Feature: Student Overview (Management)

> **Module:** Management Dashboard — Box 6  
> **Role:** ROLE_MANAGEMENT  
> **Status:** MVP

---

## Purpose

Give management a clear view of how students are distributed across buses and routes — including counts, assignments, and attendance data.

---

## Display Sections

### Bus-wise Student Distribution

| Bus | Route | Capacity | Students Assigned | Occupancy |
|---|---|---|---|---|
| DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA |

### Route-wise Student Count

| Route | Total Students |
|---|---|
| DEMO DATA | DEMO DATA |

> All sample values are DEMO DATA.

---

## Management Visibility

| Data Point | Visible |
|---|---|
| Student name | YES |
| Student ID | YES |
| Assigned bus | YES |
| Boarding stop | YES |
| Attendance record | PLANNED |
| Student contact | Subject to data policy |

---

## Data Policy Note

Student personal contact details (phone numbers) are subject to the project's data handling rules. Do not expose personal data beyond what is operationally required for management.

---

## API Endpoints

```
GET /api/management/students                          — All students with assignment
GET /api/management/students/by-bus/{busId}           — Students on a specific bus
GET /api/management/students/by-route/{routeId}       — Students on a specific route
GET /api/management/students/attendance               — Attendance overview
```

---

## Related Entities

- `Student`
- `StudentBusAssignment`
- `Bus`
- `Route`
- `Attendance`

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

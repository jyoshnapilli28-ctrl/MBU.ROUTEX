# MBU RouteX — Feature: My Bus

> **Module:** Student Dashboard — Box 1  
> **Role:** ROLE_STUDENT  
> **Status:** MVP

---

## Purpose

Display the authenticated student's assigned bus information in a clear, readable format. The student should immediately know which bus they use, its route, driver, schedule, assigned stops, and current operational status.

---

## Display Information

| Field | Description | Source |
|---|---|---|
| Bus Number | Unique bus identifier | `buses.bus_number` |
| Route | Route name and code | `routes.route_name` |
| Driver Name | Assigned driver's full name | `drivers.full_name` |
| Driver Contact | Driver phone (if authorized) | `drivers.phone` |
| Schedule | Departure time for student's stop | `trips.scheduled_departure` |
| Assigned Stop | Student's boarding stop | `route_stops.stop_name` |
| Bus Status | Current operational status | `buses.status` |

---

## Bus Status Display

| Status Value | Label Shown | Badge Color |
|---|---|---|
| ACTIVE | Active | Green |
| ON_ROUTE | On Route | Green |
| DELAYED | Delayed | Amber |
| STOPPED | Stopped | Grey |
| MAINTENANCE | Under Maintenance | Amber |
| COMPLETED | Service Completed | Neutral |

---

## Edge Cases

| Scenario | Display |
|---|---|
| Student has no bus assignment | "No bus has been assigned to your account." |
| Bus assignment exists but bus record deleted | Show error state gracefully |
| Driver not yet assigned | "Driver information not available" |

---

## API Endpoint

```
GET /api/student/bus
Authorization: ROLE_STUDENT session
Response: StudentBusDto
```

---

## Related Entities

- `StudentBusAssignment` — links student to bus
- `Bus` — bus details
- `Route` — route information
- `RouteStop` — assigned stop
- `Driver` — driver information
- `DriverAssignment` — current driver for bus

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

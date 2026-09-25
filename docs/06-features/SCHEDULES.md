# MBU RouteX — Feature: Schedules (Driver)

> **Module:** Driver Dashboard — Box 4  
> **Role:** ROLE_DRIVER  
> **Status:** MVP

---

## Purpose

Display the driver's daily schedule, route schedule, and upcoming trip assignments. Helps the driver plan and operate their service reliably.

---

## Information Displayed

| Section | Content |
|---|---|
| Today's Schedule | All trips assigned to the driver for today |
| Route Schedule | Departure times per stop for the current route |
| Trip Timings | Scheduled departure and arrival for each assigned trip |
| Upcoming Trips | Future trip assignments beyond today |

---

## Schedule Table — Today

| Trip | Route | Departure | Status |
|---|---|---|---|
| DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA |

> All sample values are DEMO DATA.

---

## Trip Schedule Entry Fields

| Field | Description |
|---|---|
| Trip ID | System trip identifier |
| Route Name | Route for this trip |
| Scheduled Departure | Planned start time |
| Scheduled Arrival | Planned end time |
| Status | SCHEDULED / ACTIVE / COMPLETED / CANCELLED |

---

## API Endpoints

```
GET /api/driver/schedules            — Full schedule
GET /api/driver/schedules/today      — Today's trips
GET /api/driver/schedules/upcoming   — Future trips
```

---

## Related Entities

- `Trip` — trip records
- `Route` — route for each trip
- `Bus` — bus for each trip
- `DriverAssignment` — confirm driver-bus relationship

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

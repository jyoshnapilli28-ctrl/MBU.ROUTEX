# MBU RouteX — Feature: Bus Details (Management)

> **Module:** Management Dashboard — Box 1  
> **Role:** ROLE_MANAGEMENT  
> **Status:** MVP

---

## Purpose

Give management a complete view of the entire bus fleet — each bus, its assigned route, assigned driver, and the number of students assigned to it.

---

## Fleet Summary Table

| Bus Number | Registration | Capacity | Route | Driver | Students | Status |
|---|---|---|---|---|---|---|
| DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA |

> All sample values are DEMO DATA.

---

## Per-Bus Detail View

When management selects a specific bus:

| Field | Description |
|---|---|
| Bus Number | Unique bus identifier |
| Registration | Vehicle registration number |
| College Serial | Internal fleet serial number |
| Capacity | Maximum passenger capacity |
| Status | Current operational status |
| Assigned Route | Route name and code |
| Assigned Driver | Driver name and ID |
| Student Count | Number of students currently assigned |
| Service Information | Additional service notes |

---

## Conceptual Data Relationship

```
Bus
 └── Route (via BusAssignment)
 └── Driver (via DriverAssignment)
 └── Students (via StudentBusAssignment)
```

---

## API Endpoints

```
GET /api/management/buses                    — All fleet buses
GET /api/management/buses/{id}              — Specific bus details
GET /api/management/buses/{id}/students     — Students on a bus
POST /api/management/buses                  — Add new bus
PUT  /api/management/buses/{id}             — Update bus details
```

---

## Related Entities

- `Bus`
- `BusAssignment`
- `DriverAssignment`
- `StudentBusAssignment`
- `Route`
- `Driver`
- `Student`

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

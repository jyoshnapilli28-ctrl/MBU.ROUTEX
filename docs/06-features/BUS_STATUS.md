# MBU RouteX — Feature: Bus Status (Driver)

> **Module:** Driver Dashboard — Box 1  
> **Role:** ROLE_DRIVER  
> **Status:** MVP

---

## Purpose

Allow the driver to view and update the operational status of their assigned bus. Status changes are visible to management in real time.

---

## Valid Status Values

| Status | Display Label | Badge Colour |
|---|---|---|
| ACTIVE | Active | Green |
| ON_ROUTE | On Route | Green |
| DELAYED | Delayed | Amber |
| STOPPED | Stopped | Grey |
| MAINTENANCE | Under Maintenance | Amber |
| COMPLETED | Service Completed | Neutral |

---

## Driver Actions

| Action | Description |
|---|---|
| View current status | See the current status of their assigned bus |
| Update status | Select new status from the list of valid values |
| Confirm update | System saves and timestamp the change |

---

## Business Rules

- A driver can only update the status of their own assigned bus
- Status changes must be logged with a timestamp
- Management can view all status changes

---

## API Endpoints

```
GET /api/driver/status     — Get current bus status
PUT /api/driver/status     — Update bus status
  Body: { "status": "DELAYED" }
```

---

## Related Entities

- `Bus` — `buses.status` column
- `DriverAssignment` — to confirm which bus belongs to the authenticated driver
- `Trip` — status may correlate with active trip status

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

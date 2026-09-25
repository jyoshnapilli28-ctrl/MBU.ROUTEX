# MBU RouteX — Feature: Trip Management (Management)

> **Module:** Management Dashboard — Box 2  
> **Role:** ROLE_MANAGEMENT  
> **Status:** PLANNED

---

## Purpose

Provide management with a live operational view of all active bus trips — where each bus is, its current status, and estimated arrival information.

---

## Active Trip Table

| Bus | Route | Driver | Status | ETA | Next Stop |
|---|---|---|---|---|---|
| DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA | DEMO DATA |

> All sample values are DEMO DATA. ETA values must come from real application data only.

---

## Trip Detail View

| Field | Description |
|---|---|
| Bus Number | Bus on this trip |
| Route | Route being operated |
| Driver | Driver on this trip |
| Scheduled Departure | When the trip was due to start |
| Actual Departure | When it actually started |
| Current Status | ACTIVE / ON_ROUTE / DELAYED / COMPLETED / CANCELLED |
| Next Stop | Next scheduled stop |
| ETA | Estimated arrival at destination (from application data) |
| Trip Completion | Whether the trip is complete |

---

## Management Actions

| Action | Description |
|---|---|
| View all trips | See full trip list |
| Filter active | View only trips currently in progress |
| View trip detail | Full information for a specific trip |
| Update trip status | Mark trip as CANCELLED if needed |

---

## ETA Note

> ETA values must be derived from real application data (scheduled times, actual departure times, delays).  
> Do not display fictional ETAs.  
> If no ETA is calculable, display "ETA not available".

---

## API Endpoints

```
GET /api/management/trips                   — All trips
GET /api/management/trips/active            — Active trips only
GET /api/management/trips/{id}              — Specific trip
POST /api/management/trips                  — Create trip
PUT  /api/management/trips/{id}/status      — Update trip status
```

---

## Related Entities

- `Trip`
- `Bus`
- `Route`
- `RouteStop`
- `Driver`

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

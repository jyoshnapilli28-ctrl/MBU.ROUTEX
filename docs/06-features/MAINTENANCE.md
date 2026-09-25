# MBU RouteX — Feature: Maintenance (Management)

> **Module:** Management Dashboard — Box 4  
> **Role:** ROLE_MANAGEMENT  
> **Status:** MVP

---

## Purpose

Track the maintenance history and scheduled maintenance for all buses in the fleet. Ensure vehicles remain roadworthy and maintenance obligations are not missed.

---

## Maintenance Record Fields

| Field | Description |
|---|---|
| Bus | Bus this record belongs to |
| Component | What was serviced (Tyre / Brake / Engine / General / Other) |
| Description | Detailed notes about the maintenance |
| Service Date | Date maintenance was performed |
| Next Service Date | When next service is due |
| Status | SCHEDULED / IN_PROGRESS / COMPLETED / OVERDUE |
| Recorded By | Management user who logged the record |

---

## Component Categories

| Component Key | Display Label |
|---|---|
| TYRE | Tyres |
| BRAKE | Brakes |
| ENGINE | Engine |
| GENERAL_SERVICE | General Service |
| ELECTRICAL | Electrical |
| BODY | Body / Chassis |
| OTHER | Other |

---

## Maintenance Status

| Status | Meaning |
|---|---|
| SCHEDULED | Maintenance is planned but not yet started |
| IN_PROGRESS | Maintenance is currently being performed |
| COMPLETED | Maintenance has been completed |
| OVERDUE | Next service date has passed without completion |

---

## Upcoming Maintenance View

Lists all buses with a `next_service_date` in the near future, sorted by urgency.

---

## Management Actions

| Action | Description |
|---|---|
| View all records | Full maintenance history |
| View by bus | Filter records by specific bus |
| Add record | Log a new maintenance event |
| Update record | Update status or add notes |
| View upcoming | See what maintenance is due soon |

---

## API Endpoints

```
GET  /api/management/maintenance              — All records
GET  /api/management/maintenance/upcoming     — Upcoming maintenance
GET  /api/management/maintenance/{busId}      — Records for a bus
POST /api/management/maintenance              — Add record
PUT  /api/management/maintenance/{id}         — Update record
```

---

## Related Entities

- `MaintenanceRecord`
- `Bus`
- `ManagementUser`

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

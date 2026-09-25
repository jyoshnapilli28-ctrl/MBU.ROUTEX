# MBU RouteX — Management Interface Specification

> **Status:** LOCKED (6 modules defined; Module 7 TO BE DEFINED)  
> **Version:** 1.0

---

## Overview

After successful authentication as ROLE_MANAGEMENT, the user lands on the Management Dashboard. It uses the identical visual system as all dashboards — campus hero, teal overlay, locked logo, card design. Management has the broadest data visibility in the system.

---

## Management Dashboard Layout

```
+----------------------------------------------------------+
|  [Locked MBU RouteX Logo]  Home | Notif | Track | Profile |
+----------------------------------------------------------+
|                                                           |
|    [MBU Campus Hero Photograph + Teal Overlay]            |
|    Welcome Management !!!                                 |
|                                                           |
+----------------------------------------------------------+
|                                                           |
|   +--------+  +--------+  +--------+  +--------+         |
|   |        |  |        |  |        |  |        |         |
|   |  Bus   |  |  Trip  |  |Compl-  |  | Maint- |         |
|   | Details|  | Mgmt   |  |aints   |  | enance |         |
|   +--------+  +--------+  +--------+  +--------+         |
|                                                           |
|   +--------+  +--------+  +--------------------+         |
|   |        |  |        |  |                    |         |
|   |Reports |  |Student |  | [Module 7]         |         |
|   |        |  |Overview|  | TO BE DEFINED      |         |
|   +--------+  +--------+  +--------------------+         |
|                                                           |
+----------------------------------------------------------+
|  Footer                                                   |
+----------------------------------------------------------+
```

---

## Dashboard Greeting

| Element | Value |
|---|---|
| **Greeting text** | Welcome Management !!! |
| **Position** | Over hero section |

---

## Taskbar Items

| Item | Function |
|---|---|
| Home | Returns to Management Dashboard |
| Notifications | View system notifications |
| Track Updates | Access fleet tracking overview |
| Profile | Management profile / settings |

---

## Module 1 — Bus Details

| Property | Specification |
|---|---|
| **Card label** | Bus Details |
| **Status** | MVP |
| **Access** | ROLE_MANAGEMENT only |

**Content:**
- All buses in fleet: bus number, registration/college serial number, capacity, status
- Route assigned to each bus
- Driver assigned to each bus
- Number of students assigned to each bus

**Conceptual data relationship:**
```
Bus → Route → Driver → Students Assigned
```

---

## Module 2 — Trip Management

| Property | Specification |
|---|---|
| **Card label** | Trip Management |
| **Status** | PLANNED |
| **Access** | ROLE_MANAGEMENT only |

**Content:**
- All active trips
- Bus location per trip (when GPS data available)
- Trip status per bus
- ETA / arrival estimate (from application data only)
- Next stop per bus
- Schedule
- Trip completion tracking

> ETA values must be derived from real application data only. No fictional values.

---

## Module 3 — Complaints

| Property | Specification |
|---|---|
| **Card label** | Complaints |
| **Status** | MVP |
| **Access** | ROLE_MANAGEMENT only |

**Sources of complaints:**
- Student complaints
- Driver (bus issue) complaints

**Management capabilities:**
- View all complaints
- Filter by: type, status, date, source
- Review complaint detail
- Update complaint status
- Add management response
- Resolve complaint
- Escalate complaint

**Complaint status lifecycle:**
```
OPEN → IN REVIEW → RESOLVED / ESCALATED
```

---

## Module 4 — Maintenance

| Property | Specification |
|---|---|
| **Card label** | Maintenance |
| **Status** | MVP |
| **Access** | ROLE_MANAGEMENT only |

**Content:**
- Vehicle maintenance records per bus
- Components tracked: Tyres, Brakes, Engine, General service
- Service dates
- Maintenance status
- Repair history
- Upcoming scheduled maintenance

---

## Module 5 — Reports

| Property | Specification |
|---|---|
| **Card label** | Reports |
| **Status** | PLANNED |
| **Access** | ROLE_MANAGEMENT only |

**Report types (planned):**
- Bus usage report
- Route usage report
- Trip performance report
- Attendance report
- Complaints report
- Maintenance report
- Student transportation usage
- Operational summaries

> All reports must be generated from stored application data. No fabricated data.

---

## Module 6 — Student Overview

| Property | Specification |
|---|---|
| **Card label** | Student Overview |
| **Status** | MVP |
| **Access** | ROLE_MANAGEMENT only |

**Content:**
- Students assigned to each bus
- Student count per bus
- Student count per route
- Attendance data (when available)

---

## Module 7 — Reserved

| Property | Specification |
|---|---|
| **Card label** | TO BE DEFINED |
| **Status** | TO BE DEFINED |
| **Access** | TO BE DEFINED |

> Module 7 has not yet been specified by the project owner. Do not invent functionality. The card may be displayed as a placeholder or hidden until defined.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

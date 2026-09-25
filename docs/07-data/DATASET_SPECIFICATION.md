# MBU RouteX — Dataset Specification

> **Status:** PLANNED  
> **Version:** 1.0  
> **IMPORTANT: All data in this specification is DEMO DATA / SYNTHETIC DATA**

---

## Purpose

This document specifies the minimum dataset required for a meaningful system demonstration. All values are synthetic — they do not represent any real person, vehicle, or institutional data.

---

## Minimum Required Dataset (Demo)

### Users (3 minimum)

| Role | Count | Note |
|---|---|---|
| ROLE_STUDENT | 1–5 | Demo students for testing |
| ROLE_DRIVER | 1–3 | Demo drivers for testing |
| ROLE_MANAGEMENT | 1 | Demo management user |

### Buses (2 minimum)

| Field | Demo Value |
|---|---|
| Bus Number | BUS-01, BUS-02 |
| Capacity | 45–55 |
| Status | ACTIVE |

### Routes (2 minimum)

| Field | Demo Value |
|---|---|
| Route Name | Route A — Demo, Route B — Demo |
| Route Code | RT-A, RT-B |

### Route Stops (4 per route minimum)

| Route | Stops |
|---|---|
| Route A | Stop 1 → Stop 2 → Stop 3 → MBU Campus Gate |
| Route B | Stop 1B → Stop 2B → Stop 3B → MBU Campus Gate |

### Trips (2 minimum)

| Field | Demo Value |
|---|---|
| Scheduled Departure | 08:00 AM (demo) |
| Status | SCHEDULED or ACTIVE |

### Complaints (3 minimum, mixed types)

| Type | Category | Status |
|---|---|---|
| Student | DELAY | OPEN |
| Student | BUS_CONDITION | RESOLVED |
| Driver | TYRE_PROBLEM | IN_REVIEW |

### Maintenance Records (2 minimum)

| Component | Status |
|---|---|
| TYRE | COMPLETED |
| GENERAL_SERVICE | SCHEDULED |

### Emergency Contacts (2 minimum)

| Category | Demo Name | Demo Phone |
|---|---|---|
| TRANSPORT | MBU Transport Desk (DEMO) | 0000-DEMO-001 |
| CAMPUS | MBU Security (DEMO) | 0000-DEMO-002 |

---

## Full Production Dataset

When the university provides real data:

| Data | Source | Responsible Party |
|---|---|---|
| Student list | University student records | University administration |
| Driver list | Transport department records | Transport department |
| Bus fleet list | Transport department | Transport department |
| Routes and stops | Transport department | Transport department |
| Emergency contacts | University safety office | University administration |

> Real data integration must be handled with appropriate data protection measures.

---

## QR Code Initialization

Each demo student account must have a QR code generated on first login or seeded during setup.

---

*ALL VALUES IN THIS DOCUMENT ARE DEMO DATA — MBU RouteX — TRACK · TRAVEL · CONNECT*

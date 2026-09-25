# MBU RouteX — Student Interface Specification

> **Status:** LOCKED  
> **Version:** 1.0

---

## Overview

After successful authentication as ROLE_STUDENT, the user lands on the Student Dashboard. The interface uses the exact same visual system as the homepage — same campus hero, same teal overlay, same logo, same card system.

---

## Student Dashboard Layout

```
+----------------------------------------------------------+
|  [Locked MBU RouteX Logo]  Home | Notif | Track | Profile |
+----------------------------------------------------------+
|                                                           |
|    [MBU Campus Hero Photograph + Teal Overlay]            |
|    Hello MBUIans !!!                                      |
|                                                           |
+----------------------------------------------------------+
|                                                           |
|   +----------+   +----------+   +----------+             |
|   |          |   |          |   |          |             |
|   |  My Bus  |   |  Live    |   | Complaint|             |
|   |          |   |  Track   |   |          |             |
|   +----------+   +----------+   +----------+             |
|                                                           |
|   +----------+   +-------------------+                   |
|   |          |   |                   |                   |
|   | Emergency|   | Attendance–My QR  |                   |
|   |          |   |                   |                   |
|   +----------+   +-------------------+                   |
|                                                           |
+----------------------------------------------------------+
|  Footer                                                   |
+----------------------------------------------------------+
```

---

## Dashboard Greeting

| Element | Value |
|---|---|
| **Greeting text** | Hello MBUIans !!! |
| **Position** | Over hero section |
| **Typography** | Consistent with design system |

---

## Taskbar Items

| Item | Function |
|---|---|
| Home | Returns to Student Dashboard |
| Notifications | View notifications relevant to student |
| Track Updates | Quick access to live tracking |
| Profile | Student profile view / settings |

---

## Module 1 — My Bus

| Property | Specification |
|---|---|
| **Card label** | My Bus |
| **Icon** | Bus icon |
| **Status** | LOCKED |
| **Access** | ROLE_STUDENT only |

**Content displayed:**
- Bus number
- Route name / number
- Driver name
- Driver contact (where authorized)
- Schedule (departure times)
- Assigned stops
- Bus operational status

---

## Module 2 — Live Track

| Property | Specification |
|---|---|
| **Card label** | Live Track |
| **Icon** | Location/map icon |
| **Status** | PLANNED |
| **Access** | ROLE_STUDENT only |

**Content displayed:**
- Current bus location (map or text representation)
- Route currently active
- Current stop
- Next stop
- ETA (Estimated Time of Arrival)
- Trip status

---

## Module 3 — Complaint

| Property | Specification |
|---|---|
| **Card label** | Complaint |
| **Icon** | Complaint/message icon |
| **Status** | PLANNED |
| **Access** | ROLE_STUDENT only |

**Complaint form categories:**
- Delay
- Driver behaviour
- Bus condition
- Overcrowding
- Route issue
- Cleanliness
- Other

**Student can:**
- Submit new complaint
- View own submitted complaints
- Check complaint status

---

## Module 4 — Emergency

| Property | Specification |
|---|---|
| **Card label** | Emergency |
| **Icon** | Emergency / alert icon |
| **Status** | MVP |
| **Access** | ROLE_STUDENT only |
| **Colour** | Emergency card may use restrained red accent |

**Content displayed:**
- Transport emergency contact
- Driver contact (where authorized)
- Campus emergency contact
- Basic safety instructions

---

## Module 5 — Attendance – My QR

| Property | Specification |
|---|---|
| **Card label** | Attendance – My QR |
| **Icon** | QR code icon |
| **Status** | PLANNED |
| **Access** | ROLE_STUDENT only |

**Content displayed:**
- Student's unique QR code (large, scannable)
- Scan/check-in status
- Attendance history (date, trip, status)

---

## Navigation Rules

The Student Dashboard must NOT display management-level operations.  
The student sees only their own data at all times.  
No cross-role data is accessible.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

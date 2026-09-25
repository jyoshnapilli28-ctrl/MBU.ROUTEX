# MBU RouteX — Driver Interface Specification

> **Status:** LOCKED  
> **Version:** 1.0

---

## Overview

After successful authentication as ROLE_DRIVER, the user lands on the Driver Dashboard. The interface uses the identical visual system as all other dashboards — same campus hero photograph, same teal overlay, same logo, same card design, same taskbar pattern. Only the content changes.

---

## Driver Dashboard Layout

```
+----------------------------------------------------------+
|  [Locked MBU RouteX Logo]  Home | Notif | Track | Profile |
+----------------------------------------------------------+
|                                                           |
|    [MBU Campus Hero Photograph + Teal Overlay]            |
|    Hello Drivers !!!                                      |
|                                                           |
+----------------------------------------------------------+
|                                                           |
|   +----------+   +----------+   +----------+             |
|   |          |   |          |   |          |             |
|   | Bus      |   | Service  |   | Bus      |             |
|   | Status   |   | Details  |   | Complaints|            |
|   +----------+   +----------+   +----------+             |
|                                                           |
|   +----------+   +----------+                            |
|   |          |   |          |                            |
|   | Schedules|   | Emergency|                            |
|   |          |   |          |                            |
|   +----------+   +----------+                            |
|                                                           |
+----------------------------------------------------------+
|  Footer                                                   |
+----------------------------------------------------------+
```

---

## Dashboard Greeting

| Element | Value |
|---|---|
| **Greeting text** | Hello Drivers !!! |
| **Position** | Over hero section |
| **Typography** | Consistent with design system |

---

## Taskbar Items

| Item | Function |
|---|---|
| Home | Returns to Driver Dashboard |
| Notifications | View notifications relevant to driver |
| Track Updates | Access to tracking/trip updates |
| Profile | Driver profile view / settings |

---

## Module 1 — Bus Status

| Property | Specification |
|---|---|
| **Card label** | Bus Status |
| **Icon** | Bus / status indicator icon |
| **Status** | MVP |
| **Access** | ROLE_DRIVER only |

**Content displayed:**
- Current bus status (Active / On Route / Delayed / Stopped / Maintenance / Completed)
- Driver can update bus status
- Status change is logged and visible to management

**Valid status values:**

| Status | Meaning |
|---|---|
| Active | Bus is ready and operational |
| On Route | Bus is currently running a trip |
| Delayed | Bus is running behind schedule |
| Stopped | Bus has stopped (not at scheduled stop) |
| Maintenance | Bus is under maintenance |
| Completed | Trip/service has been completed |

---

## Module 2 — Service Details

| Property | Specification |
|---|---|
| **Card label** | Service Details |
| **Icon** | Route/service icon |
| **Status** | MVP |
| **Access** | ROLE_DRIVER only |

**Content displayed:**
- Assigned bus (number, registration)
- Route details (route name, stops in order)
- Driver's own information
- Current trip details
- Stop list

---

## Module 3 — Bus Complaints

| Property | Specification |
|---|---|
| **Card label** | Bus Complaints |
| **Icon** | Wrench / tool icon |
| **Status** | MVP |
| **Access** | ROLE_DRIVER only |

**Issue categories:**
- Tyre problem
- Brake problem
- Engine problem
- Mechanical issue
- Cleanliness
- Bus damage
- Other

**Driver can:**
- Report a new bus issue
- View previously submitted reports and their status

---

## Module 4 — Schedules

| Property | Specification |
|---|---|
| **Card label** | Schedules |
| **Icon** | Calendar / clock icon |
| **Status** | MVP |
| **Access** | ROLE_DRIVER only |

**Content displayed:**
- Daily schedule
- Route schedule (stops and timings)
- Trip timings
- Assigned trips (current and upcoming)
- Next trip information

---

## Module 5 — Emergency

| Property | Specification |
|---|---|
| **Card label** | Emergency |
| **Icon** | Emergency / alert icon |
| **Status** | MVP |
| **Access** | ROLE_DRIVER only |
| **Colour** | Emergency card may use restrained red accent |

**Content displayed:**
- Emergency contacts (transport management)
- Campus emergency services contact
- Safety procedures
- On-road emergency protocol

---

## Navigation Rules

The Driver Dashboard must NOT display student-level or management-level operations.  
The driver sees only their own assigned bus and service data.  
No cross-role data is accessible.  
The role FACULTY must not appear anywhere — the correct role is DRIVER.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Project Master Specification

> **Status:** LOCKED  
> **Version:** 1.0  
> **Last Updated:** 2026-09-24  
> **Authority:** Project Owner

---

## Project Identity

| Field | Value |
|---|---|
| **Project Name** | MBU RouteX |
| **Tagline** | TRACK · TRAVEL · CONNECT |
| **Type** | University Transportation Management and Tracking Web Application |
| **Institution** | Mohan Babu University (MBU) |
| **Logo** | Locked MBU RouteX Logo (see Brand Identity section) |
| **Status** | Active Development |

---

## Purpose

MBU RouteX is a university transportation platform designed to provide students, drivers, and transport management personnel with a centralized system for:

- Bus information
- Route information
- Live tracking
- Complaints management
- Emergency support
- Attendance tracking
- Transport operations management

---

## Technology Stack

### Backend
| Technology | Purpose |
|---|---|
| Java | Primary programming language |
| Spring Boot | Application framework |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Data persistence layer |
| Hibernate | ORM framework |
| Maven | Build and dependency management |

### Database
| Technology | Purpose |
|---|---|
| MySQL | Primary relational database |

### Frontend
| Technology | Purpose |
|---|---|
| HTML | Page structure and markup |
| CSS | Styling (locked design system) |
| JavaScript | Client-side interactivity |

### Version Control
| Technology | Purpose |
|---|---|
| Git | Source control |
| GitHub | Remote repository and collaboration |

### Architecture
| Pattern | Description |
|---|---|
| Modular Monolith | Initial implementation architecture |

---

## Locked Brand Identity

> The MBU RouteX logo is a MASTER BRAND ASSET. It must never be redesigned, replaced, or altered in any documentation, interface, or implementation.

- **Logo reference:** Locked MBU RouteX Logo
- **Tagline:** TRACK · TRAVEL · CONNECT
- **Motivational text:** DREAM · BELIEVE · ACHIEVE
- The M/X route symbol, road shape, and location pin are locked elements
- Proportions and visual identity must not change

---

## Locked Color Palette

| Swatch | Hex Code | Role |
|---|---|---|
| Deep Teal | `#577376` | Primary brand / headers |
| Medium Teal | `#698F92` | Secondary / accents |
| Muted Teal | `#8BA7A8` | Tertiary / borders |
| Light Muted | `#AAB9BA` | Dividers / subtle elements |
| Pale Teal | `#C5D3D4` | Backgrounds / inactive |
| Near White | `#E5EBEB` | Page background |
| White | `#FFFFFF` | Cards / content surfaces |

**Semantic additions (where required only):**
- Success: restrained green
- Warning: restrained amber
- Error / Emergency: restrained red
- Inactive: neutral grey

---

## Role Architecture

The application defines three authenticated user roles:

| Role Constant | Display Name | Portal |
|---|---|---|
| `ROLE_STUDENT` | Student | Student Portal |
| `ROLE_DRIVER` | Driver | Driver Portal |
| `ROLE_MANAGEMENT` | Management | Management Portal |

> WARNING: The role FACULTY does not exist in this system. The correct role is DRIVER. This must be consistent across all documentation and code.

---

## Authenticated Dashboards — Summary

### Student Dashboard
- **Greeting:** Hello MBUIans !!!
- **Modules (5):** My Bus · Live Track · Complaint · Emergency · Attendance – My QR

### Driver Dashboard
- **Greeting:** Hello Drivers !!!
- **Modules (5):** Bus Status · Service Details · Bus Complaints · Schedules · Emergency

### Management Dashboard
- **Greeting:** Welcome Management !!!
- **Modules (6+1):** Bus Details · Trip Management · Complaints · Maintenance · Reports · Student Overview · *(Module 7: TO BE DEFINED)*

---

## Common Dashboard Design Contract

All authenticated dashboards **must share** the following without exception:

- Locked MBU RouteX Logo
- Locked color palette (`#577376` through `#FFFFFF`)
- Same typography system
- Same hero treatment (campus photograph + teal overlay)
- Same card design language
- Same icon language
- Same spacing system
- Same responsive behavior

Only role-specific content changes between dashboards.

---

## Taskbar — All Roles

Each authenticated role has an identical taskbar structure:

| Item | Student | Driver | Management |
|---|---|---|---|
| Home | YES | YES | YES |
| Notifications | YES | YES | YES |
| Track Updates | YES | YES | YES |
| Profile | YES | YES | YES |

---

## Implementation Status Classification

| Status | Meaning |
|---|---|
| `LOCKED` | Design/requirement is fixed and must not change |
| `DESIGNED` | Interface design is complete |
| `PLANNED` | Feature is scoped but not yet implemented |
| `MVP` | Required for minimum viable product |
| `FUTURE` | Planned for a future release |
| `TO BE DEFINED` | Awaiting project owner specification |

---

## Data Policy

- No real student, driver, or management personal data to be invented
- Sample/test data must be clearly labelled **DEMO DATA** or **SYNTHETIC DATA**
- Real geographical locations may be used as representatives only if verified
- Official bus numbers, schedules, and route assignments must not be invented

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

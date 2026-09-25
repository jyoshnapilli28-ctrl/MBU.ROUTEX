# MBU RouteX — Locked UI Specification

> **Status:** LOCKED  
> **Version:** 1.0  
> **Authority:** Project Owner

---

## Critical Notice

> The visual interface of MBU RouteX has been designed and approved by the project owner. This specification documents the locked design. Implementation teams must reproduce this design faithfully. No visual redesign, color change, logo replacement, or layout restructuring is permitted without explicit project owner approval.

---

## Brand Identity

| Element | Specification |
|---|---|
| **Logo** | Locked MBU RouteX Logo — M/X route symbol with road shape and location pin |
| **Tagline** | TRACK · TRAVEL · CONNECT |
| **Motivational** | DREAM · BELIEVE · ACHIEVE |
| **Logo placement** | Consistent across all pages and dashboards |

---

## Color System (Locked)

| Name | Hex | Usage |
|---|---|---|
| Deep Teal | `#577376` | Primary brand, headers, nav |
| Medium Teal | `#698F92` | Secondary elements, accents |
| Muted Teal | `#8BA7A8` | Borders, tertiary elements |
| Light Muted | `#AAB9BA` | Dividers, subtle backgrounds |
| Pale Teal | `#C5D3D4` | Inactive states, light backgrounds |
| Near White | `#E5EBEB` | Page backgrounds |
| White | `#FFFFFF` | Card surfaces, content areas |

Semantic:
- Success: restrained green (e.g. `#4a7c59`)
- Warning: restrained amber (e.g. `#b5860d`)
- Error/Emergency: restrained red (e.g. `#b03030`)
- Inactive: neutral grey

---

## Visual Design Language

### Permitted Visual Treatments

| Treatment | Description |
|---|---|
| Rounded cards | Subtle border-radius (8px–16px); consistent across all dashboards |
| Subtle shadows | `box-shadow: 0 2px 8px rgba(0,0,0,0.08)` — not dramatic |
| Teal overlay | Semi-transparent overlay over campus photograph |
| Clean typography | System/web font stack; clear hierarchy |
| Photographic imagery | Real MBU campus photograph as hero background |
| Consistent icons | Simple, consistent icon set — no decorative icon overuse |

### Prohibited Visual Treatments

| Treatment | Reason |
|---|---|
| Neon colors | Unprofessional; breaks palette |
| Flashy gradients | Generic SaaS appearance |
| Excessive glassmorphism | Decorative; unprofessional |
| Excessive blur | Reduces readability |
| Glowing cards | Gaming aesthetic |
| Cyberpunk styling | Incompatible with university identity |
| Oversized typography | Breaks hierarchy |
| Excessive animations | Distracting; unprofessional |
| Decorative clutter | Reduces usability |

---

## Page Inventory

| Page | Type | Status |
|---|---|---|
| Homepage (`/`) | Public | LOCKED |
| Student Login (`/login/student`) | Public Auth | LOCKED |
| Driver Login (`/login/driver`) | Public Auth | LOCKED |
| Management Login (`/login/management`) | Public Auth | LOCKED |
| Student Dashboard (`/student/dashboard`) | Authenticated | LOCKED |
| Driver Dashboard (`/driver/dashboard`) | Authenticated | LOCKED |
| Management Dashboard (`/management/dashboard`) | Authenticated | LOCKED |
| Student: My Bus | Feature view | PLANNED |
| Student: Live Track | Feature view | PLANNED |
| Student: Complaint | Feature view | PLANNED |
| Student: Emergency | Feature view | PLANNED |
| Student: Attendance QR | Feature view | PLANNED |
| Driver: Bus Status | Feature view | PLANNED |
| Driver: Service Details | Feature view | PLANNED |
| Driver: Bus Complaints | Feature view | PLANNED |
| Driver: Schedules | Feature view | PLANNED |
| Driver: Emergency | Feature view | PLANNED |
| Management: Bus Details | Feature view | PLANNED |
| Management: Trip Management | Feature view | PLANNED |
| Management: Complaints | Feature view | PLANNED |
| Management: Maintenance | Feature view | PLANNED |
| Management: Reports | Feature view | PLANNED |
| Management: Student Overview | Feature view | PLANNED |
| Access Denied | System page | PLANNED |
| Error 404 | System page | PLANNED |

---

## Shared Dashboard Layout

Every authenticated dashboard uses this layout:

```
+----------------------------------------------------------+
|  [Locked MBU RouteX Logo]   [Taskbar: Home | Notif | Track | Profile] |
+----------------------------------------------------------+
|                                                          |
|   [Campus Hero Photograph with Teal Overlay]             |
|   [Role-specific greeting text]                          |
|                                                          |
+----------------------------------------------------------+
|                                                          |
|   [ Card 1 ]  [ Card 2 ]  [ Card 3 ]                    |
|                                                          |
|   [ Card 4 ]  [ Card 5 ]  ([ Card 6+ ] — Management)    |
|                                                          |
+----------------------------------------------------------+
|  Footer: MBU RouteX branding                             |
+----------------------------------------------------------+
```

---

## Taskbar Specification

| Element | Specification |
|---|---|
| Background | Deep Teal `#577376` or White depending on layout |
| Logo | Locked MBU RouteX Logo — always visible |
| Navigation items | Home · Notifications · Track Updates · Profile |
| Typography | Clean, readable, consistent with design system |
| Mobile | Collapses to hamburger menu or bottom nav |

---

## Dashboard Card Specification

| Property | Specification |
|---|---|
| Background | White `#FFFFFF` |
| Border radius | 10px–14px |
| Shadow | Subtle: `0 2px 8px rgba(87,115,118,0.10)` |
| Icon | Consistent icon per module |
| Label | Module name — clean typography |
| Hover | Subtle lift effect: `transform: translateY(-2px)` |
| Active/click | Navigate to module detail view |

---

## Hero Treatment

| Property | Specification |
|---|---|
| Background | MBU campus entrance photograph |
| Overlay | Semi-transparent teal (`rgba(87,115,118,0.55)` approximately) |
| Overlay rule | Photograph must remain visible through overlay |
| Text | MBU RouteX, TRACK · TRAVEL · CONNECT, role greeting |

---

## Detail Documents

| Document | Location |
|---|---|
| Homepage Specification | `02-ui-ux/HOMEPAGE_SPECIFICATION.md` |
| Student Interface | `02-ui-ux/STUDENT_INTERFACE.md` |
| Driver Interface | `02-ui-ux/DRIVER_INTERFACE.md` |
| Management Interface | `02-ui-ux/MANAGEMENT_INTERFACE.md` |
| Design System | `02-ui-ux/DESIGN_SYSTEM.md` |
| Color System | `02-ui-ux/COLOR_SYSTEM.md` |
| Typography | `02-ui-ux/TYPOGRAPHY.md` |
| Responsive Design | `02-ui-ux/RESPONSIVE_DESIGN.md` |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

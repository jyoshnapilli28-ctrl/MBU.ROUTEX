# MBU RouteX — Homepage Specification

> **Status:** LOCKED  
> **Version:** 1.0

---

## Overview

The MBU RouteX public homepage is the entry point for all users. It must be minimal, professional, and communicate the university transportation identity without becoming a transport operations dashboard.

---

## Page Structure

### Section 1 — Hero

| Element | Specification |
|---|---|
| **Background** | MBU campus entrance photograph (supplied by project owner) |
| **Overlay** | Semi-transparent teal — `rgba(87, 115, 118, 0.55)` approximately |
| **Overlay rule** | Photograph must remain clearly visible through the overlay |
| **Primary heading** | MBU RouteX |
| **Tagline** | TRACK · TRAVEL · CONNECT |
| **Secondary element** | DREAM / BELIEVE / ACHIEVE — displayed as a separate visual element on one side |
| **Logo** | Locked MBU RouteX Logo — prominently placed |
| **Scroll indicator** | Optional subtle scroll prompt |

### Hero Text Hierarchy

```
MBU RouteX                     [Large, bold — primary identity]
TRACK · TRAVEL · CONNECT       [Tagline — medium weight]

[Separate element — right or bottom]
DREAM
BELIEVE
ACHIEVE
```

---

## Section 2 — Login / Role Selection

Reached by scrolling down from the hero section.

| Element | Specification |
|---|---|
| **Section heading** | TO BE DEFINED (optional — may be minimal) |
| **Layout** | Three equal cards side by side (responsive: stacked on mobile) |
| **Card 1** | STUDENT / Student Portal |
| **Card 2** | DRIVER / Driver Portal |
| **Card 3** | MANAGEMENT / Management Portal |
| **Card action** | Click → role-specific login page |
| **Card style** | White card, rounded corners, subtle shadow, teal icon or accent |

### Role Cards

```
+------------------+  +------------------+  +------------------+
|                  |  |                  |  |                  |
|   [Student icon] |  |   [Driver icon]  |  |  [Mgmt icon]     |
|                  |  |                  |  |                  |
|    STUDENT       |  |     DRIVER       |  |   MANAGEMENT     |
|  Student Portal  |  |  Driver Portal   |  | Management Portal|
|                  |  |                  |  |                  |
|  [ Login ]       |  |  [ Login ]       |  |  [ Login ]       |
+------------------+  +------------------+  +------------------+
```

---

## Navigation Rules

The public homepage navigation must be minimal.

### PERMITTED on homepage nav
- MBU RouteX logo / home link
- About (optional, minimal)
- Contact (optional, minimal)

### PROHIBITED on homepage nav
- Buses
- Routes
- Tracking
- Complaints
- Safety
- Any transport operations feature

> These are authenticated application features and must not appear as public navigation items.

---

## Scroll Flow

```
[Page Load]
     |
     v
[Hero Section]
     |
[User scrolls down]
     |
     v
[Role Selection / Login Cards]
     |
[User clicks role card]
     |
     v
[Role-specific login page]
```

---

## Color Application (Homepage)

| Element | Color |
|---|---|
| Hero overlay | Teal `rgba(87,115,118,0.55)` |
| Hero text | White `#FFFFFF` |
| Role card background | White `#FFFFFF` |
| Role card border/accent | `#8BA7A8` or `#698F92` |
| Role card hover | Subtle shadow lift |
| Section background | `#E5EBEB` or White |

---

## What the Homepage Must NOT Be

- A transport operations dashboard
- A marketing-heavy landing page with features list
- A generic SaaS homepage
- A page with neon, flashy gradients, or glow effects

The homepage communicates: *"This is the official MBU transport platform. Choose your role to log in."*

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

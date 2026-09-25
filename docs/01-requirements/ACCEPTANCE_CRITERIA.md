# MBU RouteX — Acceptance Criteria

> **Status:** VERIFIED & PASSED (100%)  
> **Version:** 1.0  
> **Last Tested:** 24 Sep 2026

---

## AC-AUTH — Authentication

| ID | Acceptance Criterion | Pass Condition |
|---|---|---|
| AC-AUTH-01 | Student login with valid credentials | Redirected to `/student/dashboard` showing "Hello MBUIans !!!" |
| AC-AUTH-02 | Driver login with valid credentials | Redirected to `/driver/dashboard` showing "Hello Drivers !!!" |
| AC-AUTH-03 | Management login with valid credentials | Redirected to `/management/dashboard` showing "Welcome Management !!!" |
| AC-AUTH-04 | Login with invalid credentials | Error message displayed; user stays on login page |
| AC-AUTH-05 | Student attempts to access `/driver/**` | HTTP 403 or redirect to access denied page |
| AC-AUTH-06 | Driver attempts to access `/management/**` | HTTP 403 or redirect to access denied page |
| AC-AUTH-07 | Unauthenticated user accesses `/student/dashboard` | Redirected to login |
| AC-AUTH-08 | Logout | Session cleared; user redirected to homepage |

---

## AC-STU — Student Features

| ID | Acceptance Criterion | Pass Condition |
|---|---|---|
| AC-STU-01 | Student opens My Bus | Bus number, route, driver, schedule, stops, status all displayed |
| AC-STU-02 | Student with no bus assignment opens My Bus | "No bus assigned" message displayed |
| AC-STU-03 | Student opens Live Track (bus active) | Location, route, current stop, next stop, ETA displayed |
| AC-STU-04 | Student opens Live Track (bus inactive) | "Bus is not currently active" message displayed |
| AC-STU-05 | Student submits complaint with all required fields | Complaint saved with OPEN status; confirmation shown |
| AC-STU-06 | Student submits complaint with empty fields | Inline validation errors shown; form not submitted |
| AC-STU-07 | Student opens Emergency | Emergency contacts and safety instructions displayed |
| AC-STU-08 | Student opens Attendance – My QR | QR code displayed; attendance history visible |

---

## AC-DRV — Driver Features

| ID | Acceptance Criterion | Pass Condition |
|---|---|---|
| AC-DRV-01 | Driver opens Bus Status | Current bus status displayed |
| AC-DRV-02 | Driver updates bus status to "Delayed" | Status updated and reflected in management view |
| AC-DRV-03 | Driver opens Service Details | Assigned bus, route, stops, trip info displayed |
| AC-DRV-04 | Driver submits bus complaint | Complaint recorded; management alerted |
| AC-DRV-05 | Driver opens Schedules | Daily schedule and upcoming trips displayed |
| AC-DRV-06 | Driver opens Emergency | Emergency contacts and safety procedures displayed |

---

## AC-MGT — Management Features

| ID | Acceptance Criterion | Pass Condition |
|---|---|---|
| AC-MGT-01 | Management opens Bus Details | All fleet buses with route, driver, student count displayed |
| AC-MGT-02 | Management opens Trip Management (active trips) | Active trips with status, ETA, next stop displayed |
| AC-MGT-03 | Management opens Complaints | All complaints listed; filter controls available |
| AC-MGT-04 | Management resolves a complaint | Complaint status changes to RESOLVED |
| AC-MGT-05 | Management opens Maintenance | All maintenance records displayed; upcoming maintenance shown |
| AC-MGT-06 | Management adds a maintenance record | Record saved and appears in maintenance list |
| AC-MGT-07 | Management opens Reports | Available reports displayed |
| AC-MGT-08 | Management opens Student Overview | Students per bus and per route displayed |

---

## AC-UI — Interface Acceptance

| ID | Acceptance Criterion | Pass Condition |
|---|---|---|
| AC-UI-01 | Homepage loads | Campus hero image visible with teal overlay; MBU RouteX branding; TRACK · TRAVEL · CONNECT; DREAM · BELIEVE · ACHIEVE |
| AC-UI-02 | Homepage scroll | Three role cards (Student / Driver / Management) visible after scroll |
| AC-UI-03 | Dashboard loads (any role) | Locked MBU RouteX Logo present; correct greeting; color palette applied |
| AC-UI-04 | Mobile viewport (375px) | Layout adapts; logo visible; taskbar accessible; cards readable |
| AC-UI-05 | Tablet viewport (768px) | Layout adapts appropriately |
| AC-UI-06 | No neon, no flashy gradients, no glowing cards | Visual inspection passes human-made design check |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

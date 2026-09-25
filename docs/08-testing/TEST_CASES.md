# MBU RouteX — Test Cases

> **Status:** PLANNED  
> **Version:** 1.0

---

## TC-AUTH — Authentication Test Cases

| TC ID | Test Description | Input | Expected Output | Priority |
|---|---|---|---|---|
| TC-AUTH-01 | Valid student login | Correct student credentials | Redirect to `/student/dashboard`, greeting "Hello MBUIans !!!" | MVP |
| TC-AUTH-02 | Valid driver login | Correct driver credentials | Redirect to `/driver/dashboard`, greeting "Hello Drivers !!!" | MVP |
| TC-AUTH-03 | Valid management login | Correct management credentials | Redirect to `/management/dashboard`, greeting "Welcome Management !!!" | MVP |
| TC-AUTH-04 | Wrong password | Valid username, wrong password | Error message on login page | MVP |
| TC-AUTH-05 | Non-existent user | Invalid username | Generic error message | MVP |
| TC-AUTH-06 | Logout | Authenticated session, click logout | Session cleared, redirect to homepage | MVP |
| TC-AUTH-07 | Session access after logout | Navigate to dashboard after logout | Redirect to login page | MVP |

---

## TC-STU — Student Test Cases

| TC ID | Test Description | Precondition | Expected Output | Priority |
|---|---|---|---|---|
| TC-STU-01 | View My Bus | Student logged in, bus assigned | Bus number, route, driver, schedule, stop, status displayed | MVP |
| TC-STU-02 | My Bus — no assignment | Student logged in, no bus | "No bus assigned" message | MVP |
| TC-STU-03 | Submit valid complaint | Student logged in | Complaint saved, status OPEN, confirmation shown | MVP |
| TC-STU-04 | Submit complaint — empty fields | Student logged in | Validation error messages displayed | MVP |
| TC-STU-05 | View complaint history | Student has submitted complaints | List of own complaints with statuses | MVP |
| TC-STU-06 | View Emergency | Student logged in | Emergency contacts and safety info displayed | MVP |
| TC-STU-07 | View QR code | Student logged in, QR exists | QR code image displayed | MVP |
| TC-STU-08 | View attendance history | Student logged in | Attendance records listed | MVP |
| TC-STU-09 | Student accesses driver endpoint | Student logged in | HTTP 403 | MVP |
| TC-STU-10 | Student accesses management endpoint | Student logged in | HTTP 403 | MVP |

---

## TC-DRV — Driver Test Cases

| TC ID | Test Description | Precondition | Expected Output | Priority |
|---|---|---|---|---|
| TC-DRV-01 | View Bus Status | Driver logged in, bus assigned | Current bus status displayed | MVP |
| TC-DRV-02 | Update Bus Status to DELAYED | Driver logged in | Status updated and visible | MVP |
| TC-DRV-03 | View Service Details | Driver logged in | Assigned bus, route, stops, trip info displayed | MVP |
| TC-DRV-04 | Submit bus complaint | Driver logged in | Complaint saved, confirmation shown | MVP |
| TC-DRV-05 | View Schedules | Driver logged in | Daily and upcoming trips displayed | MVP |
| TC-DRV-06 | View Emergency | Driver logged in | Emergency contacts and safety procedures displayed | MVP |
| TC-DRV-07 | Driver accesses student endpoint | Driver logged in | HTTP 403 | MVP |
| TC-DRV-08 | Driver accesses management endpoint | Driver logged in | HTTP 403 | MVP |

---

## TC-MGT — Management Test Cases

| TC ID | Test Description | Precondition | Expected Output | Priority |
|---|---|---|---|---|
| TC-MGT-01 | View Bus Details | Management logged in | Fleet list with bus, route, driver, student count | MVP |
| TC-MGT-02 | View active trips | Management logged in | Active trips with status and schedule | MVP |
| TC-MGT-03 | View all complaints | Management logged in | All student and driver complaints listed | MVP |
| TC-MGT-04 | Update complaint to IN_REVIEW | Management logged in | Status updated in database | MVP |
| TC-MGT-05 | Resolve a complaint | Management logged in | Complaint status = RESOLVED | MVP |
| TC-MGT-06 | Add maintenance record | Management logged in | Record saved, appears in maintenance list | MVP |
| TC-MGT-07 | View Student Overview | Management logged in | Students per bus and per route displayed | MVP |
| TC-MGT-08 | Management accesses student endpoint | Management logged in | HTTP 403 | MVP |

---

## TC-UI — UI and Design Test Cases

| TC ID | Test Description | Expected Output | Priority |
|---|---|---|---|
| TC-UI-01 | Homepage loads | Campus hero visible, teal overlay, MBU RouteX branding | LOCKED |
| TC-UI-02 | Homepage role cards visible | Student, Driver, Management cards present after scroll | LOCKED |
| TC-UI-03 | Student dashboard — logo present | Locked MBU RouteX Logo visible | LOCKED |
| TC-UI-04 | No neon colors in any view | Visual check passes | LOCKED |
| TC-UI-05 | Mobile (375px) layout | Cards stack, logo visible, taskbar accessible | PLANNED |
| TC-UI-06 | Tablet (768px) layout | 2-column card layout, readable | PLANNED |
| TC-UI-07 | Desktop (1280px) layout | Full dashboard layout with 3+ columns | PLANNED |
| TC-UI-08 | Color palette compliance | Only `#577376` to `#FFFFFF` and semantic additions used | LOCKED |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

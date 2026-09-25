# MBU RouteX — Project Scope

> **Status:** LOCKED  
> **Version:** 1.0

---

## In Scope

### Public Interface
- Public homepage with campus hero image and teal overlay
- TRACK · TRAVEL · CONNECT branding
- DREAM · BELIEVE · ACHIEVE motivational element
- Scroll-to-login flow with three role cards (Student / Driver / Management)
- Role-based login pages

### Student Portal
- Student authentication (ROLE_STUDENT)
- Student Dashboard with greeting: Hello MBUIans !!!
- My Bus — assigned bus information
- Live Track — real-time bus location view
- Complaint — transportation complaint submission
- Emergency — emergency contacts and safety information
- Attendance – My QR — QR code display and attendance history

### Driver Portal
- Driver authentication (ROLE_DRIVER)
- Driver Dashboard with greeting: Hello Drivers !!!
- Bus Status — operational status view and update
- Service Details — assigned service information
- Bus Complaints — bus issue reporting
- Schedules — daily and trip schedule view
- Emergency — emergency contacts

### Management Portal
- Management authentication (ROLE_MANAGEMENT)
- Management Dashboard with greeting: Welcome Management !!!
- Bus Details — fleet and route information
- Trip Management — live operational monitoring
- Complaints — review and manage all complaints
- Maintenance — vehicle maintenance tracking
- Reports — operational reports
- Student Overview — student-bus assignment visibility
- Module 7: **TO BE DEFINED**

### Cross-Cutting Concerns
- Role-based authentication and authorization
- Shared design system across all dashboards
- QR code generation for student attendance
- Notification system (basic)
- Responsive design (Desktop / Laptop / Tablet / Mobile)
- Accessibility compliance

---

## Out of Scope (Initial Release)

| Item | Notes |
|---|---|
| Real-time GPS hardware integration | FUTURE — GPS data source to be defined |
| Mobile native application (iOS/Android) | FUTURE |
| Payment / fee management | Not part of transport management scope |
| Academic data (grades, courses) | Outside transport domain |
| External third-party transport APIs | FUTURE |
| Multi-university support | Single institution scope |
| SMS / WhatsApp notifications | FUTURE |
| Parent portal | FUTURE |

---

## Scope Constraints

1. **No public transport navigation** — The public homepage must not display Buses, Routes, Tracking, Complaints, or Safety as public navigation items. These are authenticated features.

2. **No role invention** — The system has exactly three roles: STUDENT, DRIVER, MANAGEMENT. No additional roles may be introduced without project owner approval.

3. **No dashboard redesign** — The locked interface design must not be altered.

4. **No Module 7 invention** — Management Module 7 is TO BE DEFINED by the project owner.

5. **Synthetic data only** — All test/sample data must be labelled DEMO DATA or SYNTHETIC DATA.

---

## Scope Change Process

Any scope change requires explicit project owner approval.

Document scope changes with:
- Change description
- Justification
- Impact assessment
- Approval record

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Functional Requirements

> **Status:** PLANNED  
> **Version:** 1.0

---

## FR-AUTH — Authentication

| ID | Requirement | Priority |
|---|---|---|
| FR-AUTH-01 | The system shall display three role selection cards on the homepage: Student, Driver, Management | MVP |
| FR-AUTH-02 | Each role card shall navigate to a role-specific login page | MVP |
| FR-AUTH-03 | The system shall authenticate users with username and password | MVP |
| FR-AUTH-04 | The system shall assign a role (ROLE_STUDENT / ROLE_DRIVER / ROLE_MANAGEMENT) on authentication | MVP |
| FR-AUTH-05 | After successful login, the system shall redirect the user to their role-specific dashboard | MVP |
| FR-AUTH-06 | The system shall deny access to dashboards belonging to other roles | MVP |
| FR-AUTH-07 | The system shall support secure logout | MVP |
| FR-AUTH-08 | Failed login attempts shall display a clear error message | MVP |

---

## FR-STU — Student Features

### FR-STU-BUS — My Bus

| ID | Requirement | Priority |
|---|---|---|
| FR-STU-BUS-01 | The system shall display the student's assigned bus number | MVP |
| FR-STU-BUS-02 | The system shall display the route assigned to the student's bus | MVP |
| FR-STU-BUS-03 | The system shall display the driver name assigned to the student's bus | MVP |
| FR-STU-BUS-04 | The system shall display driver contact information where authorized | MVP |
| FR-STU-BUS-05 | The system shall display the bus schedule | MVP |
| FR-STU-BUS-06 | The system shall display the student's assigned stops | MVP |
| FR-STU-BUS-07 | The system shall display the current bus status | MVP |

### FR-STU-TRACK — Live Track

| ID | Requirement | Priority |
|---|---|---|
| FR-STU-TRACK-01 | The system shall display the assigned bus's current location | PLANNED |
| FR-STU-TRACK-02 | The system shall display the current route on a map | PLANNED |
| FR-STU-TRACK-03 | The system shall display the current stop | PLANNED |
| FR-STU-TRACK-04 | The system shall display the next stop | PLANNED |
| FR-STU-TRACK-05 | The system shall display estimated time of arrival (ETA) | PLANNED |
| FR-STU-TRACK-06 | The system shall display the current trip status | PLANNED |

### FR-STU-COMP — Complaint

| ID | Requirement | Priority |
|---|---|---|
| FR-STU-COMP-01 | The student shall be able to submit a transportation complaint | MVP |
| FR-STU-COMP-02 | Complaint categories shall include: Delay, Driver behaviour, Bus condition, Overcrowding, Route issue, Cleanliness, Other | MVP |
| FR-STU-COMP-03 | The student shall be able to view their submitted complaints | MVP |
| FR-STU-COMP-04 | The system shall display complaint status (Open / In Review / Resolved) | MVP |

### FR-STU-EMRG — Emergency

| ID | Requirement | Priority |
|---|---|---|
| FR-STU-EMRG-01 | The student shall have immediate access to emergency contacts | MVP |
| FR-STU-EMRG-02 | Transport emergency contact shall be displayed | MVP |
| FR-STU-EMRG-03 | Driver contact shall be displayed (where authorized) | MVP |
| FR-STU-EMRG-04 | Campus emergency contact shall be displayed | MVP |
| FR-STU-EMRG-05 | Basic safety instructions shall be displayed | MVP |

### FR-STU-QR — Attendance – My QR

| ID | Requirement | Priority |
|---|---|---|
| FR-STU-QR-01 | The system shall generate a unique QR code for each student | MVP |
| FR-STU-QR-02 | The student shall be able to view their QR code on the dashboard | MVP |
| FR-STU-QR-03 | The system shall record attendance when the QR is scanned | MVP |
| FR-STU-QR-04 | The student shall be able to view their attendance history | MVP |

---

## FR-DRV — Driver Features

### FR-DRV-STATUS — Bus Status

| ID | Requirement | Priority |
|---|---|---|
| FR-DRV-STATUS-01 | The driver shall be able to view the current status of their assigned bus | MVP |
| FR-DRV-STATUS-02 | The driver shall be able to update the bus status | MVP |
| FR-DRV-STATUS-03 | Valid status values: Active / On Route / Delayed / Stopped / Maintenance / Completed | MVP |

### FR-DRV-SVC — Service Details

| ID | Requirement | Priority |
|---|---|---|
| FR-DRV-SVC-01 | The driver shall be able to view their assigned bus details | MVP |
| FR-DRV-SVC-02 | The driver shall be able to view the route for the current service | MVP |
| FR-DRV-SVC-03 | The driver shall be able to view their assigned stops | MVP |
| FR-DRV-SVC-04 | The driver shall be able to view current trip information | MVP |

### FR-DRV-COMP — Bus Complaints

| ID | Requirement | Priority |
|---|---|---|
| FR-DRV-COMP-01 | The driver shall be able to report a bus-related issue | MVP |
| FR-DRV-COMP-02 | Issue categories shall include: Tyre problem, Brake problem, Engine problem, Mechanical issue, Cleanliness, Bus damage, Other | MVP |
| FR-DRV-COMP-03 | The driver shall be able to view previously submitted reports | MVP |

### FR-DRV-SCHED — Schedules

| ID | Requirement | Priority |
|---|---|---|
| FR-DRV-SCHED-01 | The driver shall be able to view their daily schedule | MVP |
| FR-DRV-SCHED-02 | The driver shall be able to view route schedule | MVP |
| FR-DRV-SCHED-03 | The driver shall be able to view trip timings | MVP |
| FR-DRV-SCHED-04 | The driver shall be able to view upcoming assigned trips | MVP |

### FR-DRV-EMRG — Emergency

| ID | Requirement | Priority |
|---|---|---|
| FR-DRV-EMRG-01 | The driver shall have access to emergency contacts | MVP |
| FR-DRV-EMRG-02 | Transport management contact shall be displayed | MVP |
| FR-DRV-EMRG-03 | Campus emergency services contact shall be displayed | MVP |
| FR-DRV-EMRG-04 | Safety procedures shall be accessible | MVP |

---

## FR-MGT — Management Features

### FR-MGT-BUS — Bus Details

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-BUS-01 | Management shall be able to view all buses in the fleet | MVP |
| FR-MGT-BUS-02 | Bus details shall include: bus number, registration, capacity, status | MVP |
| FR-MGT-BUS-03 | Management shall be able to view the route assigned to each bus | MVP |
| FR-MGT-BUS-04 | Management shall be able to view the driver assigned to each bus | MVP |
| FR-MGT-BUS-05 | Management shall be able to view the number of students assigned to each bus | MVP |

### FR-MGT-TRIP — Trip Management

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-TRIP-01 | Management shall be able to view all active trips | MVP |
| FR-MGT-TRIP-02 | Trip view shall show bus location | PLANNED |
| FR-MGT-TRIP-03 | Trip view shall show ETA / arrival estimates | PLANNED |
| FR-MGT-TRIP-04 | Trip view shall show next stop | PLANNED |
| FR-MGT-TRIP-05 | Trip view shall show trip status and completion | MVP |

### FR-MGT-COMP — Complaints

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-COMP-01 | Management shall be able to view all complaints | MVP |
| FR-MGT-COMP-02 | Management shall be able to filter complaints by type, status, and date | MVP |
| FR-MGT-COMP-03 | Management shall be able to update complaint status | MVP |
| FR-MGT-COMP-04 | Management shall be able to add a response to a complaint | MVP |
| FR-MGT-COMP-05 | Management shall be able to resolve or escalate complaints | MVP |

### FR-MGT-MAINT — Maintenance

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-MAINT-01 | Management shall be able to view maintenance records for all vehicles | MVP |
| FR-MGT-MAINT-02 | Maintenance records shall include: component, date, status, notes | MVP |
| FR-MGT-MAINT-03 | Upcoming maintenance shall be displayed | MVP |
| FR-MGT-MAINT-04 | Management shall be able to add and update maintenance records | MVP |

### FR-MGT-RPT — Reports

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-RPT-01 | Management shall be able to view operational reports | PLANNED |
| FR-MGT-RPT-02 | Report types: Bus usage, Route usage, Trip performance, Attendance, Complaints, Maintenance | PLANNED |
| FR-MGT-RPT-03 | Reports shall be based on stored application data only | PLANNED |

### FR-MGT-STOV — Student Overview

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-STOV-01 | Management shall be able to view students assigned to each bus | MVP |
| FR-MGT-STOV-02 | Management shall be able to view student counts per bus | MVP |
| FR-MGT-STOV-03 | Management shall be able to view student counts per route | MVP |
| FR-MGT-STOV-04 | Management shall be able to view student attendance data | PLANNED |

### FR-MGT-MOD7 — Module 7

| ID | Requirement | Priority |
|---|---|---|
| FR-MGT-MOD7-01 | TO BE DEFINED by project owner | TO BE DEFINED |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

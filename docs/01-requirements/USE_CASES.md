# MBU RouteX — Use Cases

> **Status:** PLANNED  
> **Version:** 1.0

---

## UC-01: Student Login

| Field | Detail |
|---|---|
| **Use Case ID** | UC-01 |
| **Name** | Student Login |
| **Actor** | Student |
| **Precondition** | Student has a registered account with ROLE_STUDENT |
| **Main Flow** | 1. Student visits homepage 2. Clicks Student role card 3. Enters username and password 4. System authenticates credentials 5. System checks role is ROLE_STUDENT 6. System redirects to Student Dashboard |
| **Alternative Flow** | 4a. Invalid credentials → display error message, stay on login page |
| **Postcondition** | Student is authenticated and on Student Dashboard |
| **Status** | MVP |

---

## UC-02: Driver Login

| Field | Detail |
|---|---|
| **Use Case ID** | UC-02 |
| **Name** | Driver Login |
| **Actor** | Driver |
| **Precondition** | Driver has a registered account with ROLE_DRIVER |
| **Main Flow** | 1. Driver visits homepage 2. Clicks Driver role card 3. Enters credentials 4. System authenticates 5. System confirms ROLE_DRIVER 6. Redirects to Driver Dashboard |
| **Alternative Flow** | 4a. Invalid credentials → error message |
| **Postcondition** | Driver authenticated on Driver Dashboard |
| **Status** | MVP |

---

## UC-03: Management Login

| Field | Detail |
|---|---|
| **Use Case ID** | UC-03 |
| **Name** | Management Login |
| **Actor** | Management user |
| **Precondition** | Management account with ROLE_MANAGEMENT exists |
| **Main Flow** | 1. Management user visits homepage 2. Clicks Management role card 3. Enters credentials 4. System authenticates 5. Confirms ROLE_MANAGEMENT 6. Redirects to Management Dashboard |
| **Alternative Flow** | 4a. Invalid credentials → error message |
| **Postcondition** | Management user authenticated on Management Dashboard |
| **Status** | MVP |

---

## UC-04: Student Views Bus Information

| Field | Detail |
|---|---|
| **Use Case ID** | UC-04 |
| **Name** | View My Bus |
| **Actor** | Student (ROLE_STUDENT) |
| **Precondition** | Student is logged in and has a bus assignment |
| **Main Flow** | 1. Student clicks My Bus 2. System retrieves student's bus assignment 3. System displays: bus number, route, driver, schedule, stops, status |
| **Alternative Flow** | 2a. No bus assigned → display "No bus assigned" message |
| **Postcondition** | Student has viewed their bus details |
| **Status** | MVP |

---

## UC-05: Student Views Live Track

| Field | Detail |
|---|---|
| **Use Case ID** | UC-05 |
| **Name** | Live Track Bus |
| **Actor** | Student (ROLE_STUDENT) |
| **Precondition** | Student is logged in; bus is active on a trip |
| **Main Flow** | 1. Student clicks Live Track 2. System retrieves current bus location data 3. System displays current location, route, current stop, next stop, ETA |
| **Alternative Flow** | 2a. No active trip → display "Bus is not currently active" |
| **Postcondition** | Student has viewed live bus position |
| **Status** | PLANNED |

---

## UC-06: Student Submits Complaint

| Field | Detail |
|---|---|
| **Use Case ID** | UC-06 |
| **Name** | Submit Complaint |
| **Actor** | Student (ROLE_STUDENT) |
| **Precondition** | Student is logged in |
| **Main Flow** | 1. Student clicks Complaint 2. Student selects complaint category 3. Student enters complaint description 4. Student submits form 5. System records complaint with timestamp and OPEN status 6. Confirmation displayed |
| **Alternative Flow** | 4a. Validation fails → inline error messages |
| **Postcondition** | Complaint is recorded and visible to management |
| **Status** | MVP |

---

## UC-07: Student Accesses Emergency

| Field | Detail |
|---|---|
| **Use Case ID** | UC-07 |
| **Name** | Access Emergency Contacts |
| **Actor** | Student (ROLE_STUDENT) |
| **Precondition** | Student is logged in |
| **Main Flow** | 1. Student clicks Emergency 2. System displays emergency contacts and safety instructions |
| **Alternative Flow** | None |
| **Postcondition** | Student has viewed emergency information |
| **Status** | MVP |

---

## UC-08: Student Views QR Code

| Field | Detail |
|---|---|
| **Use Case ID** | UC-08 |
| **Name** | View Attendance QR |
| **Actor** | Student (ROLE_STUDENT) |
| **Precondition** | Student is logged in; QR code exists for student |
| **Main Flow** | 1. Student clicks Attendance – My QR 2. System retrieves student's QR code 3. QR code is displayed 4. Student can view attendance history |
| **Alternative Flow** | 2a. QR not yet generated → system generates and displays |
| **Postcondition** | Student has their QR code displayed |
| **Status** | MVP |

---

## UC-09: Driver Updates Bus Status

| Field | Detail |
|---|---|
| **Use Case ID** | UC-09 |
| **Name** | Update Bus Status |
| **Actor** | Driver (ROLE_DRIVER) |
| **Precondition** | Driver is logged in and has an assigned bus |
| **Main Flow** | 1. Driver clicks Bus Status 2. System shows current status 3. Driver selects new status from: Active / On Route / Delayed / Stopped / Maintenance / Completed 4. Driver confirms 5. System updates status |
| **Alternative Flow** | 4a. Validation error → error message |
| **Postcondition** | Bus status is updated and visible to management |
| **Status** | MVP |

---

## UC-10: Driver Reports Bus Issue

| Field | Detail |
|---|---|
| **Use Case ID** | UC-10 |
| **Name** | Report Bus Complaint |
| **Actor** | Driver (ROLE_DRIVER) |
| **Precondition** | Driver is logged in |
| **Main Flow** | 1. Driver clicks Bus Complaints 2. Driver selects issue category 3. Driver describes the issue 4. Driver submits 5. System records complaint and notifies management |
| **Alternative Flow** | 4a. Validation fails → error message |
| **Postcondition** | Bus issue is recorded and management is alerted |
| **Status** | MVP |

---

## UC-11: Management Reviews Complaints

| Field | Detail |
|---|---|
| **Use Case ID** | UC-11 |
| **Name** | Manage Complaints |
| **Actor** | Management (ROLE_MANAGEMENT) |
| **Precondition** | Management user is logged in |
| **Main Flow** | 1. Management clicks Complaints 2. System displays all complaints 3. Management filters by type/status 4. Management opens a complaint 5. Management updates status / adds response 6. Management resolves or escalates |
| **Alternative Flow** | 2a. No complaints → "No complaints recorded" |
| **Postcondition** | Complaint status is updated |
| **Status** | MVP |

---

## UC-12: Management Monitors Trip

| Field | Detail |
|---|---|
| **Use Case ID** | UC-12 |
| **Name** | Monitor Trip |
| **Actor** | Management (ROLE_MANAGEMENT) |
| **Precondition** | Management user is logged in; trips are active |
| **Main Flow** | 1. Management clicks Trip Management 2. System displays all active trips 3. System shows bus location, status, ETA, next stop for each trip |
| **Alternative Flow** | 2a. No active trips → display appropriate message |
| **Postcondition** | Management has live operational view |
| **Status** | PLANNED |

---

## UC-13: Management Records Maintenance

| Field | Detail |
|---|---|
| **Use Case ID** | UC-13 |
| **Name** | Record Maintenance |
| **Actor** | Management (ROLE_MANAGEMENT) |
| **Precondition** | Management user is logged in |
| **Main Flow** | 1. Management clicks Maintenance 2. Selects a bus 3. Adds maintenance record: component, date, status, notes 4. Submits 5. System saves record |
| **Alternative Flow** | 4a. Validation fails → error message |
| **Postcondition** | Maintenance record is saved |
| **Status** | MVP |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

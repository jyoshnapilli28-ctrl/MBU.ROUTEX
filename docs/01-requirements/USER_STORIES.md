# MBU RouteX — User Stories

> **Status:** PLANNED  
> **Version:** 1.0  
> **Format:** As a [role], I want to [action], so that [benefit]

---

## Authentication Stories

| ID | Story | Priority |
|---|---|---|
| US-AUTH-01 | As a student, I want to log in using the Student Portal so that I can access my transport information | MVP |
| US-AUTH-02 | As a driver, I want to log in using the Driver Portal so that I can access my service details | MVP |
| US-AUTH-03 | As a management user, I want to log in using the Management Portal so that I can monitor and manage transport operations | MVP |
| US-AUTH-04 | As any user, I want to log out securely so that my account is protected when I finish using the system | MVP |
| US-AUTH-05 | As any user, I want to see a clear error message when my login fails so that I know what went wrong | MVP |

---

## Student Stories

### My Bus

| ID | Story | Priority |
|---|---|---|
| US-STU-01 | As a student, I want to see my assigned bus number so that I know which bus to board | MVP |
| US-STU-02 | As a student, I want to see my bus route so that I know where it travels | MVP |
| US-STU-03 | As a student, I want to see my driver's name so that I can identify my driver | MVP |
| US-STU-04 | As a student, I want to see my bus schedule so that I can plan my travel | MVP |
| US-STU-05 | As a student, I want to see my assigned boarding stops so that I know where to wait | MVP |
| US-STU-06 | As a student, I want to see my bus's current operational status so that I know if it is running | MVP |

### Live Track

| ID | Story | Priority |
|---|---|---|
| US-STU-07 | As a student, I want to see my bus's current location so that I know how far away it is | PLANNED |
| US-STU-08 | As a student, I want to see the ETA for my bus so that I can time my arrival at the stop | PLANNED |
| US-STU-09 | As a student, I want to see the next stop so that I can follow the journey | PLANNED |

### Complaint

| ID | Story | Priority |
|---|---|---|
| US-STU-10 | As a student, I want to submit a complaint about a transport issue so that management is informed | MVP |
| US-STU-11 | As a student, I want to choose a complaint category so that my issue is classified correctly | MVP |
| US-STU-12 | As a student, I want to view my submitted complaints so that I can follow up | MVP |
| US-STU-13 | As a student, I want to see the status of my complaint so that I know if it has been resolved | MVP |

### Emergency

| ID | Story | Priority |
|---|---|---|
| US-STU-14 | As a student, I want to see emergency contact numbers so that I can get help immediately in a crisis | MVP |
| US-STU-15 | As a student, I want to see campus emergency contacts so that I know who to call on campus | MVP |
| US-STU-16 | As a student, I want to see basic safety instructions so that I know what to do in an emergency | MVP |

### Attendance – My QR

| ID | Story | Priority |
|---|---|---|
| US-STU-17 | As a student, I want to see my personal QR code so that I can scan in for bus attendance | MVP |
| US-STU-18 | As a student, I want to see my attendance history so that I can verify my travel records | MVP |

---

## Driver Stories

### Bus Status

| ID | Story | Priority |
|---|---|---|
| US-DRV-01 | As a driver, I want to view my assigned bus status so that I know the current operational state | MVP |
| US-DRV-02 | As a driver, I want to update my bus status so that management is informed in real time | MVP |

### Service Details

| ID | Story | Priority |
|---|---|---|
| US-DRV-03 | As a driver, I want to see my assigned bus details so that I know my vehicle information | MVP |
| US-DRV-04 | As a driver, I want to see my route so that I know the stops I need to cover | MVP |
| US-DRV-05 | As a driver, I want to see my current trip information so that I can operate efficiently | MVP |

### Bus Complaints

| ID | Story | Priority |
|---|---|---|
| US-DRV-06 | As a driver, I want to report a bus mechanical issue so that maintenance is notified | MVP |
| US-DRV-07 | As a driver, I want to choose a problem category so that my report is classified correctly | MVP |
| US-DRV-08 | As a driver, I want to view my previously submitted reports so that I can track their resolution | MVP |

### Schedules

| ID | Story | Priority |
|---|---|---|
| US-DRV-09 | As a driver, I want to view my daily schedule so that I know my trips for the day | MVP |
| US-DRV-10 | As a driver, I want to see upcoming assigned trips so that I can prepare in advance | MVP |

### Emergency

| ID | Story | Priority |
|---|---|---|
| US-DRV-11 | As a driver, I want quick access to transport management emergency contact so that I can report on-road incidents | MVP |
| US-DRV-12 | As a driver, I want access to campus emergency services contact so that I can call for help when needed | MVP |
| US-DRV-13 | As a driver, I want to see safety procedures so that I know the correct protocol in an emergency | MVP |

---

## Management Stories

### Bus Details

| ID | Story | Priority |
|---|---|---|
| US-MGT-01 | As management, I want to view all buses in the fleet so that I have a complete operational picture | MVP |
| US-MGT-02 | As management, I want to see the route, driver, and student count for each bus so that I can monitor assignments | MVP |

### Trip Management

| ID | Story | Priority |
|---|---|---|
| US-MGT-03 | As management, I want to see all active trips so that I know which buses are currently running | MVP |
| US-MGT-04 | As management, I want to see the ETA for each bus so that I can communicate arrival times | PLANNED |
| US-MGT-05 | As management, I want to see trip status so that I know if any trip is delayed or completed | MVP |

### Complaints

| ID | Story | Priority |
|---|---|---|
| US-MGT-06 | As management, I want to view all complaints from students and drivers so that I can manage issues centrally | MVP |
| US-MGT-07 | As management, I want to filter complaints by type and status so that I can prioritize my response | MVP |
| US-MGT-08 | As management, I want to update complaint status and add responses so that complainants receive feedback | MVP |
| US-MGT-09 | As management, I want to resolve or escalate complaints so that all issues are formally closed | MVP |

### Maintenance

| ID | Story | Priority |
|---|---|---|
| US-MGT-10 | As management, I want to view maintenance records for all buses so that I can track vehicle health | MVP |
| US-MGT-11 | As management, I want to see upcoming maintenance schedules so that I can plan service disruptions | MVP |
| US-MGT-12 | As management, I want to add and update maintenance records so that the records stay current | MVP |

### Reports

| ID | Story | Priority |
|---|---|---|
| US-MGT-13 | As management, I want to view operational reports so that I can assess transport system performance | PLANNED |
| US-MGT-14 | As management, I want to view attendance reports so that I can review student travel patterns | PLANNED |

### Student Overview

| ID | Story | Priority |
|---|---|---|
| US-MGT-15 | As management, I want to see which students are assigned to which bus so that I can verify assignments | MVP |
| US-MGT-16 | As management, I want to see student counts per bus and per route so that I can assess load distribution | MVP |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

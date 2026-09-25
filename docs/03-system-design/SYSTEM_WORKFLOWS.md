# MBU RouteX — System Workflows

> **Status:** PLANNED  
> **Version:** 1.0

---

## WF-01: Student Complete Journey

```mermaid
flowchart TD
    A[Student visits MBU RouteX] --> B[Sees homepage hero]
    B --> C[Scrolls to role selection]
    C --> D[Clicks Student Portal]
    D --> E[Student login page]
    E --> F[Enters credentials]
    F --> G{Auth valid?}
    G -->|No| H[Error message]
    H --> E
    G -->|Yes| I[Student Dashboard — Hello MBUIans !!!]
    I --> J{Student chooses module}
    J --> K[My Bus]
    J --> L[Live Track]
    J --> M[Complaint]
    J --> N[Emergency]
    J --> O[Attendance – My QR]
```

---

## WF-02: Driver Complete Journey

```mermaid
flowchart TD
    A[Driver visits MBU RouteX] --> B[Clicks Driver Portal]
    B --> C[Driver login page]
    C --> D[Enters credentials]
    D --> E{Auth valid?}
    E -->|No| F[Error message]
    E -->|Yes| G[Driver Dashboard — Hello Drivers !!!]
    G --> H{Driver chooses module}
    H --> I[Bus Status]
    H --> J[Service Details]
    H --> K[Bus Complaints]
    H --> L[Schedules]
    H --> M[Emergency]
```

---

## WF-03: Management Complete Journey

```mermaid
flowchart TD
    A[Management visits MBU RouteX] --> B[Clicks Management Portal]
    B --> C[Management login page]
    C --> D[Enters credentials]
    D --> E{Auth valid?}
    E -->|No| F[Error message]
    E -->|Yes| G[Management Dashboard — Welcome Management !!!]
    G --> H{Management chooses module}
    H --> I[Bus Details]
    H --> J[Trip Management]
    H --> K[Complaints]
    H --> L[Maintenance]
    H --> M[Reports]
    H --> N[Student Overview]
    H --> O[Module 7 — TO BE DEFINED]
```

---

## WF-04: Complaint Lifecycle

```mermaid
flowchart LR
    A[Student/Driver submits complaint] --> B[Complaint created — status: OPEN]
    B --> C[Management views complaint]
    C --> D[Management reviews — status: IN REVIEW]
    D --> E{Resolution}
    E -->|Resolved| F[Status: RESOLVED]
    E -->|Needs escalation| G[Status: ESCALATED]
    F --> H[Complainant sees resolved status]
    G --> I[Escalated to senior management — TO BE DEFINED]
```

---

## WF-05: Trip Lifecycle

```mermaid
flowchart LR
    A[Management creates trip/schedule] --> B[Driver sees trip in Schedules]
    B --> C[Driver updates bus status: Active]
    C --> D[Bus departs — status: On Route]
    D --> E[Driver updates stops as covered]
    E --> F{Issues?}
    F -->|Yes| G[Driver reports delay or issue]
    G --> H[Bus status: Delayed or Stopped]
    H --> I[Management alerted]
    F -->|No| J[Trip completes normally]
    J --> K[Driver sets status: Completed]
    K --> L[Trip record closed]
```

---

## WF-06: QR Attendance Flow

```mermaid
flowchart TD
    A[Student opens Attendance – My QR] --> B[System displays student QR]
    B --> C[Driver or staff scans QR at bus]
    C --> D[System validates QR]
    D --> E{Valid?}
    E -->|Yes| F[Attendance recorded for student]
    F --> G[Student sees updated attendance history]
    E -->|No| H[Error — invalid or expired QR]
```

---

## WF-07: Emergency Access

```mermaid
flowchart TD
    A[Student/Driver opens Emergency] --> B[System displays emergency contacts]
    B --> C[User sees: Transport contact / Campus emergency / Driver contact]
    C --> D[User uses contact as needed]
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

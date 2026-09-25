# MBU RouteX — Feature: Reports (Management)

> **Module:** Management Dashboard — Box 5  
> **Role:** ROLE_MANAGEMENT  
> **Status:** PLANNED

---

## Purpose

Allow management to view and generate operational reports about the transport system. All reports must be generated from actual stored application data.

---

## Report Types (Planned)

| Report Type | Description |
|---|---|
| Bus Usage | How often each bus has been used |
| Route Usage | Usage statistics per route |
| Trip Performance | On-time vs delayed trips |
| Attendance | Student attendance summary |
| Complaints | Complaint volume and resolution statistics |
| Maintenance | Maintenance history and compliance |
| Student Transportation | Students using transport per period |
| Operational Summary | Overall transport operations summary |

---

## Report Generation

Reports are generated from stored database data:
- `trips` table for trip performance
- `attendance` table for attendance reports
- `complaints` table for complaint reports
- `maintenance_records` for maintenance reports
- `student_bus_assignments` for student transport usage

> Reports must never contain fabricated data. Data must come from the application database.

---

## Report Output

Reports may be displayed as:
- Table view on screen
- Printable formatted page
- Export to CSV or PDF (FUTURE)

---

## API Endpoints

```
GET  /api/management/reports              — List available reports
GET  /api/management/reports/{type}       — View a specific report
POST /api/management/reports/generate     — Generate a new report
  Body: { "type": "BUS_USAGE", "periodFrom": "2026-09-01", "periodTo": "2026-09-30" }
```

---

## Related Entities

- `Report` — generated report records
- `ManagementUser` — who generated it
- Various source tables (see above)

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

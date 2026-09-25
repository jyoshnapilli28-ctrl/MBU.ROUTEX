# MBU RouteX — Test Plan

> **Status:** PLANNED  
> **Version:** 1.0

---

## Test Scope

All MVP features must be tested before academic submission.

| Module | Unit | Integration | Security | Acceptance | UI |
|---|---|---|---|---|---|
| Authentication | YES | YES | YES | YES | YES |
| My Bus | YES | YES | YES | YES | YES |
| Live Track | YES | — | YES | YES | YES |
| Complaint (Student) | YES | YES | YES | YES | YES |
| Emergency (Student) | YES | YES | YES | YES | YES |
| Attendance – My QR | YES | YES | YES | YES | YES |
| Bus Status (Driver) | YES | YES | YES | YES | YES |
| Service Details | YES | YES | YES | YES | YES |
| Bus Complaints | YES | YES | YES | YES | YES |
| Schedules | YES | YES | YES | YES | YES |
| Emergency (Driver) | YES | YES | YES | YES | YES |
| Bus Details (Mgmt) | YES | YES | YES | YES | YES |
| Trip Management | YES | YES | YES | YES | YES |
| Complaints (Mgmt) | YES | YES | YES | YES | YES |
| Maintenance | YES | YES | YES | YES | YES |
| Reports | YES | — | YES | YES | YES |
| Student Overview | YES | YES | YES | YES | YES |
| Security (Cross-role) | — | — | YES | YES | — |
| Responsive UI | — | — | — | — | YES |

---

## Test Entry Criteria

- Database schema created and loaded with DEMO DATA
- Spring Boot application starts without errors
- DEMO DATA users (student, driver, management) exist and login works

## Test Exit Criteria

- All MVP test cases pass
- No critical security test failures
- Responsive layout verified on mobile, tablet, desktop
- No raw stack traces exposed to users
- All dashboards display correct greetings and modules

---

## Defect Classification

| Severity | Description | Resolution Time |
|---|---|---|
| Critical | Security breach, data corruption, auth bypass | Immediate |
| High | Feature not working, wrong data displayed | Before submission |
| Medium | UI inconsistency, minor functional issue | Before submission |
| Low | Minor style/wording issue | Best effort |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

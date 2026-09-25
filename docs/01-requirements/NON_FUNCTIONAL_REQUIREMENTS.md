# MBU RouteX — Non-Functional Requirements

> **Status:** PLANNED  
> **Version:** 1.0

---

## NFR-SEC — Security

| ID | Requirement | Priority |
|---|---|---|
| NFR-SEC-01 | All passwords must be stored using BCrypt hashing | MVP |
| NFR-SEC-02 | All API endpoints must require authentication except the public homepage and login | MVP |
| NFR-SEC-03 | Role-based access control must prevent cross-role data access | MVP |
| NFR-SEC-04 | All forms must be protected against SQL injection via parameterized queries | MVP |
| NFR-SEC-05 | All output must be encoded to prevent XSS attacks | MVP |
| NFR-SEC-06 | CSRF protection must be considered and documented | MVP |
| NFR-SEC-07 | Sensitive data (passwords, contact info) must not be exposed in API responses | MVP |
| NFR-SEC-08 | All production communication must use HTTPS | PLANNED |
| NFR-SEC-09 | Session must expire after an appropriate inactivity period | MVP |
| NFR-SEC-10 | Unauthorized access attempts must return HTTP 401 | MVP |
| NFR-SEC-11 | Access denied attempts must return HTTP 403 | MVP |

---

## NFR-PERF — Performance

| ID | Requirement | Priority |
|---|---|---|
| NFR-PERF-01 | Dashboard pages shall load within 3 seconds under normal university network conditions | MVP |
| NFR-PERF-02 | API responses shall complete within 2 seconds for standard data queries | MVP |
| NFR-PERF-03 | Live tracking data (when implemented) shall refresh within an acceptable interval | PLANNED |
| NFR-PERF-04 | QR code display shall be immediate on dashboard load | MVP |

---

## NFR-REL — Reliability

| ID | Requirement | Priority |
|---|---|---|
| NFR-REL-01 | The system shall be available during university operational hours | MVP |
| NFR-REL-02 | Database connection failures shall display a user-friendly error page | MVP |
| NFR-REL-03 | The system shall not expose raw stack traces to end users | MVP |
| NFR-REL-04 | Failed operations shall roll back database transactions cleanly | MVP |

---

## NFR-USE — Usability

| ID | Requirement | Priority |
|---|---|---|
| NFR-USE-01 | The interface shall follow the locked design system | LOCKED |
| NFR-USE-02 | The interface shall be navigable without requiring a manual or training | MVP |
| NFR-USE-03 | Error messages shall be clear, specific, and actionable | MVP |
| NFR-USE-04 | The interface shall support keyboard navigation | MVP |
| NFR-USE-05 | The interface shall meet WCAG 2.1 AA colour contrast requirements | PLANNED |
| NFR-USE-06 | All images shall have appropriate alt text | MVP |
| NFR-USE-07 | Forms shall display inline validation messages | MVP |

---

## NFR-RESP — Responsiveness

| ID | Requirement | Priority |
|---|---|---|
| NFR-RESP-01 | The interface shall function correctly on Desktop (1280px+) | MVP |
| NFR-RESP-02 | The interface shall function correctly on Laptop (1024px–1279px) | MVP |
| NFR-RESP-03 | The interface shall function correctly on Tablet (768px–1023px) | MVP |
| NFR-RESP-04 | The interface shall function correctly on Mobile (320px–767px) | MVP |
| NFR-RESP-05 | The locked design system shall be preserved across all breakpoints | LOCKED |

---

## NFR-MAIN — Maintainability

| ID | Requirement | Priority |
|---|---|---|
| NFR-MAIN-01 | Code shall be organized into layers: Controller / Service / Repository / Entity / DTO | MVP |
| NFR-MAIN-02 | REST APIs shall follow consistent naming and HTTP verb conventions | MVP |
| NFR-MAIN-03 | All entities shall be documented in the data dictionary | MVP |
| NFR-MAIN-04 | The project shall use Maven for dependency management | MVP |
| NFR-MAIN-05 | The project shall use Git for version control with documented branching workflow | MVP |

---

## NFR-SCAL — Scalability

| ID | Requirement | Priority |
|---|---|---|
| NFR-SCAL-01 | The modular monolith architecture shall allow future extraction of modules | PLANNED |
| NFR-SCAL-02 | Database schema shall be designed to support growing data volumes | MVP |

---

## NFR-ACCESS — Accessibility

| ID | Requirement | Priority |
|---|---|---|
| NFR-ACCESS-01 | HTML shall use semantic elements (header, nav, main, section, footer) | MVP |
| NFR-ACCESS-02 | All interactive elements shall be keyboard accessible | MVP |
| NFR-ACCESS-03 | Focus states shall be clearly visible | MVP |
| NFR-ACCESS-04 | Icons shall not rely on colour alone to convey meaning | MVP |
| NFR-ACCESS-05 | Forms shall have proper label associations | MVP |
| NFR-ACCESS-06 | Screen reader support shall be considered in component design | PLANNED |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

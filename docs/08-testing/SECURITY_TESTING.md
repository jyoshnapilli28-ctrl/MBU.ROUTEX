# MBU RouteX — Security Testing

> **Status:** PLANNED  
> **Version:** 1.0

---

## ST-AUTH — Authentication Tests

| ID | Test | Expected Result |
|---|---|---|
| ST-AUTH-01 | Login with valid ROLE_STUDENT credentials | Redirect to `/student/dashboard` |
| ST-AUTH-02 | Login with valid ROLE_DRIVER credentials | Redirect to `/driver/dashboard` |
| ST-AUTH-03 | Login with valid ROLE_MANAGEMENT credentials | Redirect to `/management/dashboard` |
| ST-AUTH-04 | Login with wrong password | Stay on login page with error message |
| ST-AUTH-05 | Login with non-existent username | Stay on login page with error message |
| ST-AUTH-06 | Login error message must not reveal if user exists | Error should be generic: "Invalid credentials" |
| ST-AUTH-07 | Verify password is BCrypt hashed in DB | No plain text passwords in `users.password_hash` |
| ST-AUTH-08 | Password must not appear in any API response | Response JSON contains no password field |

---

## ST-RBAC — Role-Based Access Control Tests

| ID | Test | Expected Result |
|---|---|---|
| ST-RBAC-01 | ROLE_STUDENT accesses `/student/dashboard` | 200 OK |
| ST-RBAC-02 | ROLE_STUDENT accesses `/driver/dashboard` | 403 Forbidden |
| ST-RBAC-03 | ROLE_STUDENT accesses `/management/dashboard` | 403 Forbidden |
| ST-RBAC-04 | ROLE_DRIVER accesses `/driver/dashboard` | 200 OK |
| ST-RBAC-05 | ROLE_DRIVER accesses `/student/dashboard` | 403 Forbidden |
| ST-RBAC-06 | ROLE_DRIVER accesses `/management/dashboard` | 403 Forbidden |
| ST-RBAC-07 | ROLE_MANAGEMENT accesses `/management/dashboard` | 200 OK |
| ST-RBAC-08 | ROLE_MANAGEMENT accesses `/student/dashboard` | 403 Forbidden |
| ST-RBAC-09 | ROLE_MANAGEMENT accesses `/driver/dashboard` | 403 Forbidden |
| ST-RBAC-10 | Unauthenticated request to `/student/dashboard` | 401 / Redirect to login |
| ST-RBAC-11 | ROLE_STUDENT calls `/api/management/buses` | 403 Forbidden |
| ST-RBAC-12 | ROLE_DRIVER calls `/api/student/attendance/qr` | 403 Forbidden |

---

## ST-INJ — Injection Tests

| ID | Test | Expected Result |
|---|---|---|
| ST-INJ-01 | Submit `' OR '1'='1` as username at login | Login fails; no SQL error exposed |
| ST-INJ-02 | Submit SQL payload in complaint description field | Stored safely; rendered as plain text |
| ST-INJ-03 | Submit `<script>alert('xss')</script>` in complaint | Rendered as escaped text, not executed |
| ST-INJ-04 | XSS in username field | Not stored or rendered as HTML |
| ST-INJ-05 | Verify all DB queries use parameterized statements | Code review confirms no string concatenation in queries |

---

## ST-SESSION — Session Tests

| ID | Test | Expected Result |
|---|---|---|
| ST-SESSION-01 | Log out | Session invalidated; redirect to homepage |
| ST-SESSION-02 | Access protected page after logout | Redirected to login |
| ST-SESSION-03 | Session fixation — login creates new session ID | New session ID assigned on authentication |
| ST-SESSION-04 | Concurrent login (if max session = 1) | Earlier session invalidated or blocked |

---

## ST-DATA — Sensitive Data Tests

| ID | Test | Expected Result |
|---|---|---|
| ST-DATA-01 | `GET /api/student/bus` response | No password fields in response |
| ST-DATA-02 | `GET /api/management/students` response | Only role-appropriate fields returned |
| ST-DATA-03 | Check application logs | No passwords or sensitive tokens in logs |
| ST-DATA-04 | DB credentials in source code | Not present; must use environment variables |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Testing Strategy

> **Status:** PLANNED  
> **Version:** 1.0

---

## Testing Levels

| Level | Tool | Scope |
|---|---|---|
| Unit Testing | JUnit 5 + Mockito | Service and utility classes |
| Integration Testing | Spring Boot Test + Testcontainers (optional) | API + DB layer |
| API Testing | JUnit + MockMvc | Controller endpoints |
| Security Testing | MockMvc with Security | Auth and authorization |
| Acceptance Testing | Manual + Checklist | User-facing functionality |
| UI Testing | Manual + browser | Responsive layout, design compliance |

---

## Unit Testing

Focus: Service layer business logic.

Tools:
- JUnit 5 (`@Test`, `@ExtendWith`)
- Mockito (`@Mock`, `@InjectMocks`)

Test targets:
- Complaint validation logic
- QR code generation utility
- Status transition validation
- Permission-level data filtering

---

## Integration Testing

Focus: End-to-end database and API interactions.

Tools:
- `@SpringBootTest`
- `@AutoConfigureMockMvc`
- `@Transactional` (rollback after each test)
- H2 in-memory DB or MySQL test instance

---

## API Testing

Focus: Each controller endpoint.

Tools:
- `MockMvc`
- `@WebMvcTest`

Key scenarios per endpoint:
- Correct HTTP status returned
- Correct response body structure
- Validation errors returned correctly
- Role access enforced correctly

---

## Security Testing

Focus: Authentication and authorization.

Key test cases (see `08-testing/SECURITY_TESTING.md`):
- Student cannot access driver or management endpoints
- Driver cannot access student or management endpoints
- Management cannot access student or driver personal endpoints
- Unauthenticated requests return 401
- Cross-role requests return 403
- Password not returned in any API response
- SQL injection attempts fail gracefully
- XSS payloads are encoded in output

---

## Acceptance Testing

Manual testing against acceptance criteria in `01-requirements/ACCEPTANCE_CRITERIA.md`.

Checklist approach:
- Login works for all three roles
- Dashboards display correct content
- Complaints can be submitted and viewed
- Emergency contacts are accessible
- QR code displays
- Management can update complaint status
- Maintenance records can be added

---

## UI / Design Testing

Manual review checklist:
- Locked MBU RouteX Logo present on all pages
- Color palette matches `#577376` through `#FFFFFF`
- No neon colors
- No flashy gradients
- Campus hero photograph visible with teal overlay
- Dashboard greeting correct for each role
- All 5 student modules visible
- All 5 driver modules visible
- All 6+ management modules visible
- Taskbar present with correct items
- Responsive layout works on mobile (375px), tablet (768px), desktop (1280px)

---

## Test Coverage Targets

| Layer | Coverage Target |
|---|---|
| Service layer | 80%+ |
| Controller layer | All endpoints covered |
| Security rules | All role/path combinations tested |
| Critical paths | 100% (Login, Complaint submit, QR display) |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

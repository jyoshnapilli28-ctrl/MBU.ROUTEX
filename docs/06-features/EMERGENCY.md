# MBU RouteX — Feature: Emergency

> **Roles:** ROLE_STUDENT · ROLE_DRIVER  
> **Status:** MVP

---

## Purpose

Provide immediate, single-tap access to emergency contacts and safety information for both students and drivers. This feature must be always accessible and must load without delay.

---

## Student Emergency View

| Contact / Info | Description |
|---|---|
| Transport Emergency Contact | University transport department emergency number |
| Driver Contact | Current assigned driver's contact (where authorized) |
| Campus Emergency Contact | Campus security / general emergency number |
| Safety Instructions | Brief safety guidance for students |

---

## Driver Emergency View

| Contact / Info | Description |
|---|---|
| Transport Management Contact | Transport management emergency number |
| Campus Emergency Services | Campus emergency / security contact |
| On-Road Emergency Protocol | What to do if a breakdown or accident occurs |
| Safety Procedures | Brief safety guidance for drivers |

---

## Design Rules

- Emergency card may use a restrained red accent colour (from the semantic palette)
- Must NOT use neon red or overly alarming design
- Contact information must be clearly legible at all screen sizes
- The Emergency module must be immediately accessible from the dashboard

---

## Emergency Contact Data Source

Emergency contacts are stored in the `emergency_contacts` table.

Categories:
- `TRANSPORT` — University transport department
- `CAMPUS` — Campus security / general
- `MEDICAL` — Medical emergency
- `OTHER`

> Real contact numbers must come from the university's verified emergency contact list.  
> Do NOT invent phone numbers.  
> Development/test data must be labelled **DEMO DATA**.

---

## API Endpoint

```
GET /api/student/emergency
GET /api/driver/emergency
Authorization: Role-specific session
Response: List<EmergencyContactDto>
```

---

## Related Entity

- `EmergencyContact` — stores all emergency contact records

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

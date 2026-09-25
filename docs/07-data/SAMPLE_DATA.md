# MBU RouteX — Sample Data

> **IMPORTANT: ALL DATA IN THIS FILE IS DEMO DATA / SYNTHETIC DATA**  
> **This data is for development and testing purposes only.**  
> **It does not represent any real person, vehicle, route, or institution.**

---

## DEMO DATA: Users

| ID | Username | Email | Role | Password (plain — for setup only) |
|---|---|---|---|---|
| 1 | student.demo | student@demo.mbu | ROLE_STUDENT | DemoPass@123 |
| 2 | driver.demo | driver@demo.mbu | ROLE_DRIVER | DemoPass@123 |
| 3 | mgmt.demo | management@demo.mbu | ROLE_MANAGEMENT | DemoPass@123 |

> Passwords must be BCrypt-hashed before inserting into the database.

---

## DEMO DATA: Students

| ID | User ID | Student ID | Full Name | Department | Year |
|---|---|---|---|---|---|
| 1 | 1 | MBU-STU-001 | Demo Student One | Computer Science | 3rd Year |

---

## DEMO DATA: Drivers

| ID | User ID | Driver ID | Full Name | License |
|---|---|---|---|---|
| 1 | 2 | MBU-DRV-001 | Demo Driver One | DEMO-LIC-001 |

---

## DEMO DATA: Buses

| ID | Bus Number | Registration | College Serial | Capacity | Status |
|---|---|---|---|---|---|
| 1 | BUS-01 | DEMO-REG-01 | MBU-FLEET-01 | 50 | ACTIVE |
| 2 | BUS-02 | DEMO-REG-02 | MBU-FLEET-02 | 45 | ACTIVE |

---

## DEMO DATA: Routes

| ID | Route Name | Route Code | Start | End |
|---|---|---|---|---|
| 1 | Route A — Demo | RT-A | Demo Start Point | MBU Campus |
| 2 | Route B — Demo | RT-B | Demo Start Point B | MBU Campus |

---

## DEMO DATA: Route Stops (Route A)

| Order | Stop Name |
|---|---|
| 1 | Demo Start Stop |
| 2 | Demo Stop 2 |
| 3 | Demo Stop 3 |
| 4 | MBU Campus Gate |

---

## DEMO DATA: Emergency Contacts

| Name | Role | Phone | Category |
|---|---|---|---|
| MBU Transport Desk (DEMO) | Transport Emergency | 0000-DEMO-001 | TRANSPORT |
| MBU Campus Security (DEMO) | Campus Emergency | 0000-DEMO-002 | CAMPUS |

> All phone numbers are placeholder DEMO values. Real contacts must be provided by the university.

---

## SQL Insert Statements (DEMO DATA)

```sql
-- DEMO DATA: Insert demo users (passwords must be BCrypt encoded)
INSERT INTO users (username, email, password_hash, role, is_active)
VALUES
  ('student.demo', 'student@demo.mbu', '$2a$10$BCRYPT_HASH_HERE', 'ROLE_STUDENT', true),
  ('driver.demo',  'driver@demo.mbu',  '$2a$10$BCRYPT_HASH_HERE', 'ROLE_DRIVER',  true),
  ('mgmt.demo',    'mgmt@demo.mbu',    '$2a$10$BCRYPT_HASH_HERE', 'ROLE_MANAGEMENT', true);

-- DEMO DATA: Insert demo bus
INSERT INTO buses (bus_number, registration, college_serial, capacity, status)
VALUES ('BUS-01', 'DEMO-REG-01', 'MBU-FLEET-01', 50, 'ACTIVE');

-- DEMO DATA: Insert demo route
INSERT INTO routes (route_name, route_code, start_point, end_point, is_active)
VALUES ('Route A — Demo', 'RT-A', 'Demo Start Point', 'MBU Campus', true);
```

---

*ALL VALUES IN THIS FILE ARE DEMO DATA — MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Feature: Service Details (Driver)

> **Module:** Driver Dashboard — Box 2  
> **Role:** ROLE_DRIVER  
> **Status:** MVP

---

## Purpose

Display the driver's full service information — the bus they are assigned to, the route, stops, and current trip details. This is the driver's operational reference view.

---

## Information Displayed

| Field | Description |
|---|---|
| Assigned Bus | Bus number, registration, college serial |
| Route Name | The route this bus operates |
| Route Code | Short route identifier |
| Stops List | Ordered list of stops on the route |
| Driver Information | Driver's own name and ID |
| Current Trip | Active trip details (if a trip is in progress) |
| Trip Status | Current trip status |

---

## Stops List Display

The stops are displayed in sequence order:

```
1. [Start Stop Name]
2. [Stop 2 Name]
3. [Stop 3 Name]
   ...
N. [End Stop Name]
```

---

## Edge Cases

| Scenario | Display |
|---|---|
| No active assignment | "No bus is currently assigned to your account." |
| No active trip | "No active trip in progress." |
| Route stops not configured | "Route stops not yet configured." |

---

## API Endpoints

```
GET /api/driver/service         — Full service details
GET /api/driver/service/route   — Route details
GET /api/driver/service/stops   — Ordered stop list
```

---

## Related Entities

- `DriverAssignment` — driver-to-bus link
- `BusAssignment` — bus-to-route link
- `Bus` — bus details
- `Route` — route details
- `RouteStop` — stop list
- `Trip` — current trip reference

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

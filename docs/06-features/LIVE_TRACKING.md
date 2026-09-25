# MBU RouteX — Feature: Live Tracking

> **Module:** Student Dashboard — Box 2  
> **Role:** ROLE_STUDENT  
> **Status:** PLANNED

---

## Purpose

Allow the student to see the real-time location and journey progress of their assigned bus. This gives students confidence about when their bus will arrive.

---

## Display Information

| Field | Description |
|---|---|
| Current Location | Current position of the bus (text or map view) |
| Route | The active route |
| Current Stop | The stop the bus is at or has most recently passed |
| Next Stop | The next upcoming stop |
| ETA | Estimated time until arrival at student's boarding stop |
| Trip Status | ACTIVE / ON_ROUTE / DELAYED / etc. |

---

## GPS / Location Source

> The GPS data source for live tracking is **TO BE DEFINED** by the project owner. The Live Track feature depends on a real-time location update mechanism for buses. This may be:
> - A GPS device on each bus sending location updates
> - A driver-triggered location update from the Driver app
> - An external tracking API integration

Until the GPS source is defined, the Live Track view should gracefully handle the "no live data" state.

---

## Edge Cases

| Scenario | Display |
|---|---|
| No active trip for student's bus | "Your bus is not currently active." |
| GPS data not available | "Live location data is currently unavailable." |
| Bus delayed | Show DELAYED status with last known info |

---

## API Endpoint

```
GET /api/student/tracking
GET /api/student/tracking/eta
Authorization: ROLE_STUDENT session
```

---

## Implementation Note

The tracking interface may display:
- A text-based representation of route progress (stop-by-stop)
- A map integration (if a map library is included)
- A refresh indicator to show data freshness

Map integration is PLANNED and depends on GPS source confirmation.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

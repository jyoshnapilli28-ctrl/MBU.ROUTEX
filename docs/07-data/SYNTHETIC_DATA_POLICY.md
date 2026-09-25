# MBU RouteX — Synthetic Data Policy

> **Status:** LOCKED  
> **Version:** 1.0

---

## Policy Statement

MBU RouteX does not currently have a confirmed production dataset. All data used during development, testing, and demonstration must be clearly labelled as **DEMO DATA** or **SYNTHETIC DATA**.

---

## What Must NOT Be Invented

| Data Type | Rule |
|---|---|
| Real student names | Do not use actual student identities |
| Real student phone numbers | Do not invent or use real student phone numbers |
| Real driver phone numbers | Do not invent or use real driver phone numbers |
| Real management contact details | Do not invent or use real staff contact information |
| Official bus registration numbers | Do not use real vehicle registration plates |
| Official bus fleet numbers | Do not invent real fleet numbers |
| Official transport schedules | Do not present fictional schedules as real |
| Official route assignments | Do not invent real route data |
| Real emergency phone numbers | Do not invent real university emergency contacts |

---

## What May Be Used

| Data Type | Rule |
|---|---|
| Fictional representative names | Use clearly fictional names for demo persons |
| Representative locations | Real geographic names may be used as examples if they are representative and clearly labelled DEMO |
| Fictional bus numbers | e.g. BUS-01, BUS-02 — clearly fictional |
| Fictional route names | e.g. "Route A", "Route B" — clearly fictional |
| Placeholder schedules | e.g. "08:00 AM" — clearly labelled DEMO DATA |

---

## Labelling Requirements

When sample data appears in documentation or the system:

- Label it explicitly as **DEMO DATA**
- Label it explicitly as **SYNTHETIC DATA**
- Or note at the top of the section: "All values in this section are DEMO DATA"

---

## Production Data

When the university provides real data:
- Data must be handled with care and appropriate data protection
- Student personal data must not be exposed beyond what is operationally required
- Driver personal data must not be exposed to students
- The project owner must approve which fields are visible to which roles

---

## Sample Data Reference

See `07-data/SAMPLE_DATA.md` for development sample data — all labelled DEMO DATA.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

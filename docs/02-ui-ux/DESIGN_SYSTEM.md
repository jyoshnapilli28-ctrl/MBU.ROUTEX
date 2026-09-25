# MBU RouteX — Design System

> **Status:** LOCKED  
> **Version:** 1.0

---

## Overview

The MBU RouteX design system defines the foundational visual and structural standards that all pages and components must follow. This system is locked and must be applied consistently across the entire application.

---

## Spacing System

Use a base-8 spacing scale for all padding, margin, and gap values.

| Token | Value | Usage |
|---|---|---|
| `space-xs` | 4px | Icon padding, micro gaps |
| `space-sm` | 8px | Inner element spacing |
| `space-md` | 16px | Component padding |
| `space-lg` | 24px | Section padding, card inner space |
| `space-xl` | 32px | Section gap |
| `space-2xl` | 48px | Hero padding |
| `space-3xl` | 64px | Major section separation |

---

## Border Radius

| Token | Value | Usage |
|---|---|---|
| `radius-sm` | 6px | Input fields, small elements |
| `radius-md` | 10px | Cards, buttons |
| `radius-lg` | 14px | Larger cards |
| `radius-full` | 9999px | Badges, pills |

---

## Shadow System

| Token | Value | Usage |
|---|---|---|
| `shadow-sm` | `0 1px 3px rgba(0,0,0,0.06)` | Subtle lift |
| `shadow-md` | `0 2px 8px rgba(87,115,118,0.10)` | Dashboard cards |
| `shadow-lg` | `0 4px 16px rgba(87,115,118,0.14)` | Modals, elevated panels |

---

## Component: Dashboard Card

```css
.dashboard-card {
  background: #FFFFFF;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(87, 115, 118, 0.10);
  padding: 24px;
  transition: transform 0.15s ease, box-shadow 0.15s ease;
  cursor: pointer;
}

.dashboard-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(87, 115, 118, 0.16);
}
```

---

## Component: Taskbar

```
[Logo]  [Nav links]                            [Profile]
```

| Property | Value |
|---|---|
| Height | 56px–64px |
| Background | `#577376` (Deep Teal) or White depending on page |
| Logo | Locked MBU RouteX Logo — always visible, never replaced |
| Nav items | Clean text links, no heavy borders |
| Active state | Understated indicator — underline or lighter tint |

---

## Component: Hero Section

| Property | Value |
|---|---|
| Height | 300px–480px depending on context |
| Background | MBU campus entrance photograph |
| Overlay | `rgba(87, 115, 118, 0.55)` |
| Text colour | `#FFFFFF` |
| Text position | Centred or left-aligned |
| Photograph rule | Must remain visible through overlay |

---

## Component: Role Cards (Homepage)

```css
.role-card {
  background: #FFFFFF;
  border: 1px solid #C5D3D4;
  border-radius: 12px;
  padding: 32px 24px;
  text-align: center;
  box-shadow: 0 2px 8px rgba(87, 115, 118, 0.08);
  transition: box-shadow 0.2s ease, transform 0.2s ease;
}

.role-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 20px rgba(87, 115, 118, 0.14);
}
```

---

## Component: Buttons

| Type | Style |
|---|---|
| Primary | Background `#577376`, text `#FFFFFF`, radius 8px |
| Secondary | Border `#8BA7A8`, text `#577376`, transparent background |
| Danger/Emergency | Restrained red, not neon |
| Disabled | Neutral grey, cursor not-allowed |

---

## Component: Forms

| Property | Value |
|---|---|
| Input border | `1px solid #AAB9BA` |
| Input focus border | `#698F92` |
| Input radius | 6px |
| Input padding | 10px 14px |
| Label | Above input, clean typography |
| Error state | Restrained red border + error message below |
| Success state | Restrained green indicator |

---

## Component: Status Badges

| Status | Colour |
|---|---|
| Active / On Route | Restrained green badge |
| Delayed | Restrained amber badge |
| Stopped | Neutral grey badge |
| Maintenance | Restrained amber badge |
| Completed | Teal/neutral badge |
| Emergency | Restrained red badge |
| Open (complaint) | Neutral badge |
| In Review | Amber badge |
| Resolved | Green badge |

---

## Icon Guidelines

- Use a consistent icon set throughout (e.g. Heroicons, Feather, or Material Icons — one set only)
- Icons must supplement text labels, not replace them
- Icon size: 20px–24px for dashboard cards; 16px–18px for inline use
- Icons must not rely on colour alone to convey meaning (accessibility rule)
- Do not use decorative icons that serve no functional purpose

---

## Grid System

- Dashboard cards: CSS Grid, auto-fill with `minmax(240px, 1fr)`
- Role cards (homepage): 3-column flex or grid, collapses on mobile
- Content sections: Max width 1200px centred
- Gutters: 16px–24px

---

## Animation Guidelines

| Animation | Allowed |
|---|---|
| Card hover lift (`transform: translateY(-2px)`) | YES |
| Button hover colour shift | YES |
| Page load fade-in | YES (subtle) |
| Scroll reveal | Minimal only |
| Flashing / blinking effects | NO |
| Spinning loaders beyond standard | NO |
| Dramatic entrance animations | NO |
| Parallax effects | NO |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Asset Library

> **Status:** COMPLETE v1.0  
> **TRACK · TRAVEL · CONNECT**

---

## Asset Directory Structure

```
assets/
├── brand/               — Logo variants, favicon, app icon, DBA text, scroll indicators
├── icons/
│   ├── common/          — System icons (add, edit, delete, save, calendar, clock…)
│   ├── navigation/      — Taskbar icons (home, notifications, track-updates, profile…)
│   ├── student/         — Student module icons (my-bus, live-track, complaint, emergency, attendance-qr…)
│   ├── driver/          — Driver module icons (bus-status, service-details, bus-complaints, schedule…)
│   ├── management/      — Management module icons (bus-details, trip-management, complaints, maintenance…)
│   ├── transport/       — Shared transport icons (bus, route, stop, driver, eta, capacity, trip…)
│   ├── tracking/        — Map markers and live-location icons
│   ├── complaints/      — Complaint category and status icons
│   ├── safety/          — Emergency and safety icons
│   └── maintenance/     — Maintenance category icons
├── illustrations/
│   ├── empty/           — Empty state SVGs (no-bus, no-route, no-complaints…)
│   ├── error/           — Error state SVGs (404, 500, unauthorized, network-error…)
│   └── loading/         — Loading animation SVGs (spinner, bus, route, data, map)
├── images/
│   ├── hero/            — campus-hero.jpg (LOCKED hero image)
│   ├── buses/           — Bus photography
│   ├── campus/          — Additional campus images
│   ├── tracking/        — Tracking-related photography
│   ├── maintenance/     — Maintenance-related photography
│   ├── safety/          — Safety-related photography
│   └── management/      — Management-related photography
├── fonts/               — FONT_CONFIGURATION.css (Inter font setup guide)
└── styles/
    ├── variables.css    — ALL design tokens (colors, spacing, radius, shadows…)
    ├── colors.css       — Color utility classes and badge styles
    ├── typography.css   — Font import, headings, brand text, helpers
    ├── spacing.css      — Margin, padding, gap, container utilities
    ├── shadows.css      — Shadow utility classes
    ├── radius.css       — Border radius utilities
    ├── borders.css      — Border and divider utilities
    ├── components.css   — Taskbar, hero, cards, buttons, forms, tables, modals
    ├── status.css       — Status dots, empty states, error states, loading, animations
    └── responsive.css   — Breakpoints, hamburger menu, responsive utilities, print
```

---

## How to Include Styles in Thymeleaf Templates

```html
<!-- In <head> — order matters -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">

<link rel="stylesheet" th:href="@{/assets/styles/variables.css}">
<link rel="stylesheet" th:href="@{/assets/styles/colors.css}">
<link rel="stylesheet" th:href="@{/assets/styles/typography.css}">
<link rel="stylesheet" th:href="@{/assets/styles/spacing.css}">
<link rel="stylesheet" th:href="@{/assets/styles/radius.css}">
<link rel="stylesheet" th:href="@{/assets/styles/shadows.css}">
<link rel="stylesheet" th:href="@{/assets/styles/borders.css}">
<link rel="stylesheet" th:href="@{/assets/styles/components.css}">
<link rel="stylesheet" th:href="@{/assets/styles/status.css}">
<link rel="stylesheet" th:href="@{/assets/styles/responsive.css}">
```

---

## Logo Usage

| Variant | Use Case |
|---|---|
| `logo-white.svg` | Taskbar (dark teal background) |
| `logo-primary.svg` | Login card, reports, white backgrounds |
| `logo-monochrome.svg` | Print, single-color use |
| `logo-symbol.svg` | Small spaces, favicon fallback |
| `favicon.svg` | Browser tab |
| `app-icon.svg` | Bookmark, PWA icon |

---

## Hero Image Usage

```html
<!-- Homepage hero -->
<section class="hero">
  <img src="/assets/images/hero/campus-hero.jpg"
       alt="MBU Campus"
       class="hero-image">
  <div class="hero-overlay"></div>
  <div class="hero-content">
    <!-- branding -->
  </div>
</section>
```

The same hero image + overlay is used on:
- Public Homepage
- Student Dashboard
- Driver Dashboard
- Management Dashboard

---

## Icon Usage Pattern

```html
<!-- Inline SVG (recommended for color control) -->
<img src="/assets/icons/student/my-bus.svg"
     alt="My Bus"
     class="module-card-icon"
     width="26" height="26">

<!-- Or embed inline for currentColor theming -->
```

---

## Color Compliance

All icons use `stroke="currentColor"` — set color via CSS `color` property.

Status-specific icons (bus-active, complaint-resolved, etc.) embed their semantic color directly in the SVG for clarity.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Responsive Design

> **Status:** PLANNED  
> **Version:** 1.0

---

## Responsive Philosophy

The locked MBU RouteX design must be **adapted** for different screen sizes — not redesigned. The visual identity, color palette, logo, hero treatment, and card system remain constant. Only layout arrangements change.

---

## Breakpoints

| Name | Width | Target Devices |
|---|---|---|
| Mobile | 320px – 767px | Smartphones |
| Tablet | 768px – 1023px | Tablets, large phones landscape |
| Laptop | 1024px – 1279px | Laptops, small desktops |
| Desktop | 1280px+ | Desktops, large monitors |

```css
/* Mobile first approach */
/* Base = Mobile */

@media (min-width: 768px)  { /* Tablet   */ }
@media (min-width: 1024px) { /* Laptop   */ }
@media (min-width: 1280px) { /* Desktop  */ }
```

---

## Homepage — Responsive Behavior

### Desktop / Laptop
- Hero: Full width, tall (400px–480px)
- DREAM · BELIEVE · ACHIEVE: Positioned on right side of hero
- Role cards: 3 cards in a single row
- Nav: Horizontal, all items visible

### Tablet
- Hero: Full width, slightly reduced height
- DREAM · BELIEVE · ACHIEVE: May reposition below branding
- Role cards: 3 cards in a row (may compress) or 2+1
- Nav: Horizontal or slightly compressed

### Mobile
- Hero: Full width, compact (250px–300px)
- DREAM · BELIEVE · ACHIEVE: Stacked below tagline
- Role cards: Stacked vertically (1 column)
- Nav: Hamburger menu or simplified header

---

## Dashboard — Responsive Behavior

### Desktop / Laptop
- Dashboard cards: 3–4 columns via CSS Grid
- Taskbar: Horizontal, full items visible
- Hero: Full-width panel with greeting

### Tablet
- Dashboard cards: 2–3 columns
- Taskbar: Horizontal, condensed
- Hero: Full-width, slightly smaller

### Mobile
- Dashboard cards: 1–2 columns
- Taskbar: Hamburger or bottom navigation
- Hero: Compact
- Cards: Full width, stacked

---

## CSS Grid — Dashboard Cards

```css
.dashboard-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 24px;
  padding: 24px;
}

@media (max-width: 767px) {
  .dashboard-grid {
    grid-template-columns: 1fr;
    padding: 16px;
    gap: 16px;
  }
}

@media (min-width: 768px) and (max-width: 1023px) {
  .dashboard-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}
```

---

## CSS Grid — Role Cards (Homepage)

```css
.role-cards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
  max-width: 900px;
  margin: 0 auto;
}

@media (max-width: 767px) {
  .role-cards {
    grid-template-columns: 1fr;
    max-width: 400px;
  }
}

@media (min-width: 768px) and (max-width: 1023px) {
  .role-cards {
    grid-template-columns: repeat(2, 1fr);
  }
}
```

---

## Elements That Must Remain Constant at All Breakpoints

| Element | Rule |
|---|---|
| Locked MBU RouteX Logo | Always visible — never hidden at any breakpoint |
| Color palette | Unchanged across all breakpoints |
| Teal hero overlay | Always applied |
| Typography system | Scaled, not replaced |
| Card design | Adapted size, same visual style |
| Role identity (STUDENT / DRIVER / MANAGEMENT) | Always clearly displayed |

---

## Taskbar — Mobile Behavior

On mobile (< 768px), the taskbar should:
- Show the Locked MBU RouteX Logo
- Collapse navigation items into a hamburger menu or icon bar
- The hamburger menu opens a side drawer or dropdown with: Home · Notifications · Track Updates · Profile
- Profile icon may remain visible as a top-right icon

---

## Image Responsiveness

```css
.hero-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}
```

The campus photograph must scale without cropping the key subject area.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

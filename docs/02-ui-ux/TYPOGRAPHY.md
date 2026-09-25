# MBU RouteX — Typography

> **Status:** LOCKED  
> **Version:** 1.0

---

## Font Stack

MBU RouteX uses a professional, clean web font stack suitable for a university institutional interface.

### Recommended Primary Font

**Inter** (Google Fonts) — clean, modern, highly readable, institutional.

```html
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
```

```css
font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
```

### Fallback Stack

If Google Fonts is unavailable, use the system font stack:

```css
font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
```

---

## Type Scale

| Level | Element | Size | Weight | Line Height | Usage |
|---|---|---|---|---|---|
| Display | `h1` hero | 2.5rem (40px) | 700 | 1.2 | Hero heading (MBU RouteX) |
| Heading 1 | `h1` page | 2rem (32px) | 700 | 1.25 | Page titles |
| Heading 2 | `h2` section | 1.5rem (24px) | 600 | 1.3 | Section headings |
| Heading 3 | `h3` card | 1.125rem (18px) | 600 | 1.4 | Card titles |
| Body Large | `p`, main | 1rem (16px) | 400 | 1.6 | Main body text |
| Body Small | `p`, secondary | 0.875rem (14px) | 400 | 1.5 | Secondary info |
| Caption | labels, meta | 0.75rem (12px) | 400 | 1.4 | Timestamps, labels |
| Button | button text | 0.9375rem (15px) | 500 | 1 | Button labels |

---

## CSS Typography Variables

```css
:root {
  /* Font family */
  --font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;

  /* Font sizes */
  --text-display: 2.5rem;
  --text-h1:      2rem;
  --text-h2:      1.5rem;
  --text-h3:      1.125rem;
  --text-body:    1rem;
  --text-sm:      0.875rem;
  --text-xs:      0.75rem;
  --text-button:  0.9375rem;

  /* Font weights */
  --weight-regular:  400;
  --weight-medium:   500;
  --weight-semibold: 600;
  --weight-bold:     700;

  /* Line heights */
  --leading-tight:  1.25;
  --leading-normal: 1.5;
  --leading-loose:  1.6;
}
```

---

## Typographic Rules

### DO
- Use clear heading hierarchy (one `h1` per page)
- Use `Inter` or system fallback consistently across all pages
- Maintain readable line lengths (45–75 characters per line for body text)
- Use font-weight 600–700 for headings, 400–500 for body
- Use `#2d3748` (dark grey) for primary body text on white backgrounds
- Use `#FFFFFF` for text on teal/dark backgrounds

### DO NOT
- Mix multiple decorative or display fonts
- Use font sizes smaller than 12px
- Use ALL-CAPS for long body text
- Use decorative script fonts
- Use light weight (300) for body text at small sizes
- Use oversized typography that breaks layout proportions

---

## Tagline Typography

| Text | Style |
|---|---|
| **MBU RouteX** | Display size, weight 700, letter-spacing 0.02em |
| **TRACK · TRAVEL · CONNECT** | Heading size, weight 500, letter-spacing 0.15em, uppercase |
| **DREAM · BELIEVE · ACHIEVE** | Heading/body size, weight 600, letter-spacing 0.12em, uppercase |

---

## Dashboard Greeting Typography

| Role | Greeting | Style |
|---|---|---|
| Student | Hello MBUIans !!! | h2 size, weight 600, white on hero |
| Driver | Hello Drivers !!! | h2 size, weight 600, white on hero |
| Management | Welcome Management !!! | h2 size, weight 600, white on hero |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

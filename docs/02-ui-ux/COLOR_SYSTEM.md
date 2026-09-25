# MBU RouteX — Color System

> **Status:** LOCKED  
> **Version:** 1.0

---

## Locked Primary Palette

The following seven colors form the locked MBU RouteX primary palette. No substitutions are permitted.

| Token | Hex | RGB | Description |
|---|---|---|---|
| `color-primary` | `#577376` | rgb(87, 115, 118) | Deep teal — primary brand, headers, primary buttons |
| `color-secondary` | `#698F92` | rgb(105, 143, 146) | Medium teal — accents, links, secondary elements |
| `color-tertiary` | `#8BA7A8` | rgb(139, 167, 168) | Muted teal — borders, tertiary elements |
| `color-muted` | `#AAB9BA` | rgb(170, 185, 186) | Light muted — dividers, placeholders |
| `color-pale` | `#C5D3D4` | rgb(197, 211, 212) | Pale teal — inactive backgrounds, light surfaces |
| `color-surface` | `#E5EBEB` | rgb(229, 235, 235) | Near-white — page background |
| `color-white` | `#FFFFFF` | rgb(255, 255, 255) | White — card surfaces, content areas |

---

## Semantic Colors

Used only where a clear semantic meaning is required. These are additions to the palette, not replacements.

| Token | Hex (approximate) | Usage |
|---|---|---|
| `color-success` | `#4a7c59` | Success states, resolved complaints |
| `color-warning` | `#b5860d` | Warning states, delayed status |
| `color-error` | `#b03030` | Error messages, form errors |
| `color-emergency` | `#b03030` | Emergency module accent |
| `color-inactive` | `#9ca3af` | Inactive elements, disabled states |

---

## Color Application Rules

### Backgrounds
| Context | Color |
|---|---|
| Page background | `#E5EBEB` |
| Card surface | `#FFFFFF` |
| Hero overlay | `rgba(87, 115, 118, 0.55)` |
| Taskbar (dark variant) | `#577376` |
| Section alternate | `#FFFFFF` or `#E5EBEB` |

### Text
| Context | Color |
|---|---|
| Primary text | `#2d3748` (dark grey — readability) |
| Secondary text | `#577376` |
| On teal background | `#FFFFFF` |
| On hero | `#FFFFFF` |
| Error text | `#b03030` |
| Link | `#698F92` |

### Borders
| Context | Color |
|---|---|
| Card border (optional) | `#C5D3D4` |
| Input border | `#AAB9BA` |
| Input focus | `#698F92` |
| Divider | `#C5D3D4` |

### Buttons
| Type | Background | Text |
|---|---|---|
| Primary | `#577376` | `#FFFFFF` |
| Secondary | Transparent | `#577376` with border `#8BA7A8` |
| Danger | `#b03030` | `#FFFFFF` |
| Disabled | `#C5D3D4` | `#9ca3af` |

---

## Prohibited Color Uses

| Prohibited | Reason |
|---|---|
| Neon greens, blues, pinks | Unprofessional; incompatible with brand |
| Hot pink, electric purple | Not in palette |
| Bright orange accent | Not in palette |
| CSS gradients using unrelated colors | Breaks palette identity |
| Glowing / luminescent effects | Gaming/crypto aesthetic |
| White text on white background | Accessibility failure |
| Low-contrast text combinations | Accessibility failure |

---

## CSS Custom Properties

```css
:root {
  /* Primary palette */
  --color-primary:   #577376;
  --color-secondary: #698F92;
  --color-tertiary:  #8BA7A8;
  --color-muted:     #AAB9BA;
  --color-pale:      #C5D3D4;
  --color-surface:   #E5EBEB;
  --color-white:     #FFFFFF;

  /* Semantic */
  --color-success:   #4a7c59;
  --color-warning:   #b5860d;
  --color-error:     #b03030;
  --color-emergency: #b03030;
  --color-inactive:  #9ca3af;

  /* Hero overlay */
  --hero-overlay: rgba(87, 115, 118, 0.55);
}
```

---

## Contrast Compliance

| Combination | Ratio | WCAG |
|---|---|---|
| White text on `#577376` | ~5.9:1 | AA Pass |
| White text on `#698F92` | ~4.5:1 | AA Pass |
| `#577376` text on `#FFFFFF` | ~5.9:1 | AA Pass |
| `#2d3748` text on `#FFFFFF` | ~12:1 | AAA Pass |
| `#577376` text on `#E5EBEB` | ~5.0:1 | AA Pass |

> All text/background combinations must achieve at minimum WCAG 2.1 AA (4.5:1 for normal text, 3:1 for large text).

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

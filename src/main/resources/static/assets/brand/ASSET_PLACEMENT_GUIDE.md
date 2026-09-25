# MBU RouteX — Locked Asset Placement Guide

> **IMPORTANT: The actual locked assets must be placed manually by the project owner.**
> The SVG files in `assets/brand/` are CSS-colorable fallbacks only.
> The MASTER files are the PNGs/JPGs supplied by the project owner.

---

## Step 1 — Place Your Actual Logo PNG

Your locked logo image (the M/X road symbol with location pin) must be placed as:

```
assets/brand/logo-primary.png
```

**Also create these copies of the same file:**

```
assets/brand/logo-symbol.png
assets/brand/app-icon.png
```

For the white/taskbar version (if you have a white-on-transparent variant):

```
assets/brand/logo-white.png
assets/brand/logo-light.png
```

---

## Step 2 — Place Your Actual Campus Hero Image

Your locked MBU campus entrance photograph (the M-shaped gate with pink and blue arches, steps, palm trees) must be placed as:

```
assets/images/hero/campus-hero.jpg
```

**This single image is used as the hero background for:**
- Public Homepage
- Student Dashboard
- Driver Dashboard
- Management Dashboard

> The teal overlay (`rgba(87, 115, 118, 0.55)`) is applied via CSS on top of it.
> The campus photograph itself is NOT modified.

---

## Step 3 — Create a Favicon from Your Logo

Export or crop your logo symbol to 32×32px and save as:

```
assets/brand/favicon.png
```

A 180×180px version for Apple devices:

```
assets/brand/apple-touch-icon.png
```

---

## Step 4 — Reference in Thymeleaf HTML

### In `<head>`:

```html
<!-- Favicon -->
<link rel="icon" type="image/svg+xml" th:href="@{/assets/brand/favicon.svg}">
<link rel="icon" type="image/png" th:href="@{/assets/brand/favicon.png}">
<link rel="apple-touch-icon" th:href="@{/assets/brand/apple-touch-icon.png}">
```

### Taskbar logo (on teal background — use white version):

```html
<a href="/" class="taskbar-logo">
  <img th:src="@{/assets/brand/logo-white.png}"
       alt="MBU RouteX"
       height="36">
</a>
```

> If white PNG not available, use `logo-white.svg` as fallback.

### Login card / white background:

```html
<img th:src="@{/assets/brand/logo-primary.png}"
     alt="MBU RouteX"
     height="56">
```

### Hero background image:

```html
<section class="hero">
  <img th:src="@{/assets/images/hero/campus-hero.jpg}"
       alt="MBU University Campus"
       class="hero-image">
  <div class="hero-overlay"></div>
  <div class="hero-content">
    <!-- branding content here -->
  </div>
</section>
```

---

## Spring Boot Static Resources Configuration

Ensure your Spring Boot project serves `assets/` as static resources.

In `application.properties`:

```properties
spring.web.resources.static-locations=classpath:/static/
```

Place your assets folder under:

```
src/main/resources/static/assets/
```

So the full path becomes:

```
src/main/resources/static/assets/brand/logo-primary.png
src/main/resources/static/assets/images/hero/campus-hero.jpg
```

---

## Summary of Files to Place Manually

| File | Source |
|---|---|
| `assets/brand/logo-primary.png` | Your actual logo PNG |
| `assets/brand/logo-white.png` | White variant of logo (if available) |
| `assets/brand/logo-symbol.png` | Symbol-only crop of logo |
| `assets/brand/favicon.png` | 32×32px logo crop |
| `assets/brand/app-icon.png` | 512×512px logo version |
| `assets/images/hero/campus-hero.jpg` | Your actual MBU campus photograph |

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

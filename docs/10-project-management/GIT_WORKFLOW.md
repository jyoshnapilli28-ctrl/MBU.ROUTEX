# MBU RouteX — Git Workflow

> **Status:** PLANNED  
> **Version:** 1.0

---

## Repository

| Field | Value |
|---|---|
| Platform | GitHub |
| Repository name | MBU.ROUTEX |
| Default branch | `main` |

---

## Branching Strategy

```
main
 └── develop
      ├── feature/student-dashboard
      ├── feature/driver-dashboard
      ├── feature/management-dashboard
      ├── feature/complaints
      ├── feature/qr-attendance
      ├── feature/live-tracking
      ├── feature/maintenance
      ├── fix/login-redirect
      └── fix/complaint-validation
```

| Branch | Purpose |
|---|---|
| `main` | Production-ready code only |
| `develop` | Integration branch — all features merge here first |
| `feature/*` | Individual feature development |
| `fix/*` | Bug fixes |
| `hotfix/*` | Critical production fixes |

---

## Commit Message Convention

```
[type]: [short description]

Types:
  feat     — New feature
  fix      — Bug fix
  docs     — Documentation change
  style    — Code style / formatting (no logic change)
  refactor — Code refactoring
  test     — Adding or updating tests
  chore    — Build, config, maintenance tasks

Examples:
  feat: add student complaint submission API
  fix: correct role redirect after management login
  docs: update API endpoints documentation
  test: add security tests for role access control
```

---

## Pull Request Process

1. Create a `feature/*` or `fix/*` branch from `develop`
2. Implement and test the change
3. Push the branch to GitHub
4. Open a Pull Request from the branch to `develop`
5. Review the PR
6. Merge to `develop`
7. When `develop` is stable and tested, merge to `main`

---

## .gitignore Essentials

```gitignore
# Maven
target/
*.class
*.jar
*.war

# IDE
.idea/
*.iml
.eclipse/
.vscode/

# Environment / secrets
application-prod.properties
.env
*.env

# OS
.DS_Store
Thumbs.db
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

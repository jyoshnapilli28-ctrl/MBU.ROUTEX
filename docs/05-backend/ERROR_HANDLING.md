# MBU RouteX — Error Handling

> **Status:** PLANNED  
> **Version:** 1.0

---

## Strategy

MBU RouteX uses a centralized exception handling approach via Spring's `@ControllerAdvice`. All exceptions are caught globally and converted into consistent, structured JSON error responses. Raw stack traces must never reach the client.

---

## Standard Error Response

All error responses follow this format:

```json
{
  "timestamp": "2026-09-24T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed: description must not be blank",
  "path": "/api/student/complaints"
}
```

---

## Custom Exception Classes

```java
// Resource not found (404)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

// Business rule violation (400)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

// Access denied (403)
public class RoleAccessException extends RuntimeException {
    public RoleAccessException(String message) {
        super(message);
    }
}
```

---

## Global Exception Handler

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest req) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest req) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .findFirst().orElse("Validation failed");
        return buildError(HttpStatus.BAD_REQUEST, message, req.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex, HttpServletRequest req) {
        return buildError(HttpStatus.FORBIDDEN, "Access denied", req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex, HttpServletRequest req) {
        // Log the exception internally — do NOT expose details to client
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred. Please try again.", req.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildError(
            HttpStatus status, String message, String path) {
        ErrorResponse err = new ErrorResponse(
            LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path);
        return ResponseEntity.status(status).body(err);
    }
}
```

---

## HTTP Status Code Usage

| Code | Scenario |
|---|---|
| 200 OK | Successful GET or update |
| 201 Created | Successful POST (resource created) |
| 204 No Content | Successful DELETE |
| 400 Bad Request | Validation failure, malformed request |
| 401 Unauthorized | Not authenticated |
| 403 Forbidden | Authenticated but wrong role |
| 404 Not Found | Requested resource does not exist |
| 409 Conflict | Duplicate entry or state conflict |
| 500 Internal Server Error | Unhandled server-side error |

---

## User-Facing Error Pages

| Page | Trigger |
|---|---|
| `/login?error=true` | Invalid login credentials |
| `/login?expired=true` | Session expired |
| `/access-denied` | 403 role access denial |
| `/error/404` | Page not found |
| `/error/500` | Server error |

All error pages must use the MBU RouteX design system — not plain browser error pages.

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

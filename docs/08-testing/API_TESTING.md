# MBU RouteX — API Testing

> **Status:** PLANNED  
> **Version:** 1.0

---

## Testing Tool

Use **MockMvc** (Spring Boot Test) for API testing within the Java test suite.

```java
@SpringBootTest
@AutoConfigureMockMvc
class StudentComplaintControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    @WithMockUser(username = "student.demo", roles = {"STUDENT"})
    void submitComplaint_validData_returns201() throws Exception {
        mockMvc.perform(post("/api/student/complaints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "category": "DELAY",
                      "description": "Bus was 30 minutes late today"
                    }
                """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    @WithMockUser(username = "student.demo", roles = {"STUDENT"})
    void submitComplaint_emptyDescription_returns400() throws Exception {
        mockMvc.perform(post("/api/student/complaints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "category": "DELAY",
                      "description": ""
                    }
                """))
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "driver.demo", roles = {"DRIVER"})
    void studentEndpoint_accessedByDriver_returns403() throws Exception {
        mockMvc.perform(get("/api/student/bus"))
            .andExpect(status().isForbidden());
    }
}
```

---

## API Test Coverage Checklist

### Student APIs

| Endpoint | Test: Valid Auth | Test: Wrong Role | Test: Invalid Input |
|---|---|---|---|
| `GET /api/student/bus` | YES | YES | N/A |
| `GET /api/student/tracking` | YES | YES | N/A |
| `GET /api/student/complaints` | YES | YES | N/A |
| `POST /api/student/complaints` | YES | YES | YES |
| `GET /api/student/emergency` | YES | YES | N/A |
| `GET /api/student/attendance/qr` | YES | YES | N/A |
| `GET /api/student/attendance` | YES | YES | N/A |

### Driver APIs

| Endpoint | Test: Valid Auth | Test: Wrong Role | Test: Invalid Input |
|---|---|---|---|
| `GET /api/driver/status` | YES | YES | N/A |
| `PUT /api/driver/status` | YES | YES | YES |
| `GET /api/driver/service` | YES | YES | N/A |
| `POST /api/driver/complaints` | YES | YES | YES |
| `GET /api/driver/schedules` | YES | YES | N/A |
| `GET /api/driver/emergency` | YES | YES | N/A |

### Management APIs

| Endpoint | Test: Valid Auth | Test: Wrong Role | Test: Invalid Input |
|---|---|---|---|
| `GET /api/management/buses` | YES | YES | N/A |
| `GET /api/management/trips/active` | YES | YES | N/A |
| `GET /api/management/complaints` | YES | YES | N/A |
| `PUT /api/management/complaints/{id}/status` | YES | YES | YES |
| `POST /api/management/maintenance` | YES | YES | YES |
| `GET /api/management/students` | YES | YES | N/A |

---

## Common Test Patterns

### Test unauthenticated access
```java
@Test
void anyProtectedEndpoint_unauthenticated_returns401or302() throws Exception {
    mockMvc.perform(get("/api/student/bus"))
        .andExpect(status().isUnauthorized()); // or .is3xxRedirection()
}
```

### Test response structure
```java
.andExpect(jsonPath("$.busNumber").exists())
.andExpect(jsonPath("$.route").exists())
.andExpect(jsonPath("$.status").exists())
```

### Test no password in response
```java
.andExpect(jsonPath("$.password").doesNotExist())
.andExpect(jsonPath("$.passwordHash").doesNotExist())
```

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

# MBU RouteX — Development Setup

> **Status:** PLANNED  
> **Version:** 1.0

---

## Prerequisites

| Software | Version | Notes |
|---|---|---|
| Java JDK | 17 or higher | Recommended: OpenJDK 17 LTS |
| Maven | 3.8+ | Or use the Maven Wrapper (`mvnw`) included in project |
| MySQL | 8.0+ | Local installation or Docker |
| Git | Any recent | For version control |
| IDE | IntelliJ IDEA or Eclipse | IntelliJ IDEA Community/Ultimate recommended |
| Browser | Chrome / Firefox / Edge | For testing frontend |

---

## Step 1: Clone the Repository

```bash
git clone https://github.com/[your-github-username]/MBU.ROUTEX.git
cd MBU.ROUTEX
```

---

## Step 2: Create the MySQL Database

```sql
CREATE DATABASE mbu_routex CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'routex_user'@'localhost' IDENTIFIED BY 'your_secure_password';
GRANT ALL PRIVILEGES ON mbu_routex.* TO 'routex_user'@'localhost';
FLUSH PRIVILEGES;
```

---

## Step 3: Configure Environment Variables

Set these environment variables (or use `application-dev.properties`):

```
DB_USERNAME=routex_user
DB_PASSWORD=your_secure_password
```

Or configure directly in `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mbu_routex?useSSL=false&serverTimezone=Asia/Kolkata
spring.datasource.username=routex_user
spring.datasource.password=your_secure_password
spring.jpa.hibernate.ddl-auto=create-drop
```

> `create-drop` for initial setup — switch to `validate` after first run.

---

## Step 4: Load DEMO DATA (Optional)

After the schema is created, load sample data from `07-data/SAMPLE_DATA.md`.

BCrypt-encode the demo passwords using Spring's `BCryptPasswordEncoder` before inserting.

---

## Step 5: Build and Run

```bash
# Using Maven Wrapper
./mvnw spring-boot:run

# Or using installed Maven
mvn spring-boot:run

# Or build JAR and run
mvn clean package -DskipTests
java -jar target/mbu-routex-*.jar
```

---

## Step 6: Access the Application

| URL | Page |
|---|---|
| `http://localhost:8080/` | Public homepage |
| `http://localhost:8080/login/student` | Student login |
| `http://localhost:8080/login/driver` | Driver login |
| `http://localhost:8080/login/management` | Management login |

---

## IDE Setup (IntelliJ IDEA)

1. Open IntelliJ IDEA → File → Open → Select the project root directory
2. IntelliJ will auto-detect the Maven project
3. Wait for Maven dependencies to download
4. Set Java SDK to 17 in File → Project Structure
5. Run `MbuRoutexApplication.java` directly

---

*MBU RouteX — TRACK · TRAVEL · CONNECT*

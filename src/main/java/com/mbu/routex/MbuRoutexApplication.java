package com.mbu.routex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;

@SpringBootApplication
public class MbuRoutexApplication {

    private static final Logger log = LoggerFactory.getLogger(MbuRoutexApplication.class);

    public static void main(String[] args) {
        resolveDatabaseEnvironment();
        SpringApplication.run(MbuRoutexApplication.class, args);
    }

    private static void resolveDatabaseEnvironment() {
        String rawUrl = System.getenv("MYSQL_URL");
        if (rawUrl == null || rawUrl.isBlank()) {
            rawUrl = System.getenv("DATABASE_URL");
        }

        if (rawUrl != null && !rawUrl.isBlank()) {
            try {
                String cleanUrl = rawUrl.startsWith("jdbc:mysql://") ? rawUrl.substring(5) : rawUrl;
                if (!cleanUrl.startsWith("mysql://")) {
                    cleanUrl = "mysql://" + cleanUrl;
                }
                URI uri = URI.create(cleanUrl);

                if (System.getProperty("MYSQLHOST") == null && System.getenv("MYSQLHOST") == null && uri.getHost() != null) {
                    System.setProperty("MYSQLHOST", uri.getHost());
                }
                if (System.getProperty("MYSQLPORT") == null && System.getenv("MYSQLPORT") == null && uri.getPort() != -1) {
                    System.setProperty("MYSQLPORT", String.valueOf(uri.getPort()));
                }
                if (System.getProperty("MYSQLDATABASE") == null && System.getenv("MYSQLDATABASE") == null && uri.getPath() != null) {
                    String path = uri.getPath();
                    if (path.startsWith("/")) path = path.substring(1);
                    if (!path.isBlank()) {
                        System.setProperty("MYSQLDATABASE", path);
                    }
                }
                if (System.getProperty("MYSQLUSER") == null && System.getenv("MYSQLUSER") == null && uri.getUserInfo() != null) {
                    String[] userInfo = uri.getUserInfo().split(":", 2);
                    if (userInfo.length > 0 && !userInfo[0].isBlank()) {
                        System.setProperty("MYSQLUSER", userInfo[0]);
                    }
                    if (userInfo.length > 1 && System.getProperty("MYSQLPASSWORD") == null && System.getenv("MYSQLPASSWORD") == null) {
                        System.setProperty("MYSQLPASSWORD", userInfo[1]);
                    }
                }
            } catch (Exception e) {
                log.warn("[MBU RouteX] Could not parse MYSQL_URL/DATABASE_URL: {}", e.getMessage());
            }
        }

        String effectiveHost = System.getProperty("MYSQLHOST", System.getenv("MYSQLHOST") != null ? System.getenv("MYSQLHOST") : "localhost");
        String effectivePort = System.getProperty("MYSQLPORT", System.getenv("MYSQLPORT") != null ? System.getenv("MYSQLPORT") : "3306");
        String effectiveDb   = System.getProperty("MYSQLDATABASE", System.getenv("MYSQLDATABASE") != null ? System.getenv("MYSQLDATABASE") : "mbu_routex");
        String effectiveUser = System.getProperty("MYSQLUSER", System.getenv("MYSQLUSER") != null ? System.getenv("MYSQLUSER") : "root");

        log.info("==================================================================");
        log.info("[MBU RouteX] Database Target: jdbc:mysql://{}:{}/{}", effectiveHost, effectivePort, effectiveDb);
        log.info("[MBU RouteX] Database Username: {}", effectiveUser);
        if ("localhost".equalsIgnoreCase(effectiveHost)) {
            log.warn("[MBU RouteX] WARNING: Connecting to 'localhost:3306'. If running on Railway, ensure MYSQLHOST or MYSQL_URL is configured in service variables!");
        }
        log.info("==================================================================");
    }
}

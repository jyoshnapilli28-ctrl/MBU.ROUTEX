package com.mbu.routex.config;

import com.mbu.routex.attendance.entity.Attendance;
import com.mbu.routex.attendance.entity.AttendanceStatus;
import com.mbu.routex.attendance.entity.QRCode;
import com.mbu.routex.attendance.repository.AttendanceRepository;
import com.mbu.routex.attendance.repository.QRCodeRepository;
import com.mbu.routex.bus.entity.Bus;
import com.mbu.routex.bus.entity.BusAssignment;
import com.mbu.routex.bus.entity.BusStatus;
import com.mbu.routex.bus.repository.BusAssignmentRepository;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.complaint.entity.Complaint;
import com.mbu.routex.complaint.entity.ComplaintStatusEnum;
import com.mbu.routex.complaint.entity.SubmitterType;
import com.mbu.routex.complaint.repository.ComplaintRepository;
import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.entity.DriverAssignment;
import com.mbu.routex.driver.repository.DriverAssignmentRepository;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.emergency.entity.EmergencyCategory;
import com.mbu.routex.emergency.entity.EmergencyContact;
import com.mbu.routex.emergency.repository.EmergencyContactRepository;
import com.mbu.routex.maintenance.entity.MaintenanceRecord;
import com.mbu.routex.maintenance.entity.MaintenanceStatus;
import com.mbu.routex.maintenance.repository.MaintenanceRecordRepository;
import com.mbu.routex.management.entity.ManagementUser;
import com.mbu.routex.management.repository.ManagementUserRepository;
import com.mbu.routex.notification.entity.Notification;
import com.mbu.routex.notification.repository.NotificationRepository;
import com.mbu.routex.route.entity.Route;
import com.mbu.routex.route.entity.RouteStop;
import com.mbu.routex.route.repository.RouteRepository;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.entity.StudentBusAssignment;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import com.mbu.routex.student.repository.StudentRepository;
import com.mbu.routex.trip.entity.Trip;
import com.mbu.routex.trip.entity.TripStatus;
import com.mbu.routex.trip.repository.TripRepository;
import com.mbu.routex.user.entity.Role;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DataSeeder — populates database with synthetic DEMO data on startup.
 *
 * Dataset:
 *   - 1 management user
 *   - 10 drivers  (DRV001–DRV010)
 *   - 50 students (STU001–STU050)
 *   - 10 buses    (MBU-001–MBU-010)
 *   - 10 external routes (Tirupati area, Andhra Pradesh)
 *   - Bus/Driver/Student assignments, trips, QR codes, attendance,
 *     complaints, maintenance records, emergency contacts, notifications
 *
 * NOTE: All data is clearly DEMO/SYNTHETIC — no real personal data.
 * Seeder runs ONLY when the users table is empty.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final ManagementUserRepository managementUserRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final BusAssignmentRepository busAssignmentRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final TripRepository tripRepository;
    private final ComplaintRepository complaintRepository;
    private final MaintenanceRecordRepository maintenanceRecordRepository;
    private final QRCodeRepository qrCodeRepository;
    private final AttendanceRepository attendanceRepository;
    private final EmergencyContactRepository emergencyContactRepository;
    private final NotificationRepository notificationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() > 0) {
            log.info("DataSeeder: Data already exists. Skipping seed.");
            return;
        }

        log.info("DataSeeder: Seeding DEMO data — 10 buses, 10 routes, 10 drivers, 50 students...");
        String enc = passwordEncoder.encode("password");

        // ── Management User ──────────────────────────────────────────────────
        User uMgmt = save(User.builder().username("management1").email("management1@mbu.demo")
                .passwordHash(enc).role(Role.ROLE_MANAGEMENT).isActive(true).build());
        ManagementUser mgmt = managementUserRepository.save(ManagementUser.builder()
                .user(uMgmt).fullName("Dr. Anita Desai").department("Transport Management").build());

        // ── Drivers (10) ──────────────────────────────────────────────────────
        String[][] driverData = {
            // {username, fullName, phone, license, driverId}
            {"driver1",  "Suresh Patil",        "AP25-DL-2018-1001", "DRV001"},
            {"driver2",  "Ramesh Yadav",         "AP25-DL-2017-1002", "DRV002"},
            {"driver3",  "Venkata Rao",          "AP25-DL-2019-1003", "DRV003"},
            {"driver4",  "Krishna Murthy",       "AP25-DL-2016-1004", "DRV004"},
            {"driver5",  "Balaji Reddy",         "AP25-DL-2020-1005", "DRV005"},
            {"driver6",  "Srinivas Naidu",       "AP25-DL-2015-1006", "DRV006"},
            {"driver7",  "Ranga Rao",            "AP25-DL-2018-1007", "DRV007"},
            {"driver8",  "Mohan Das",            "AP25-DL-2021-1008", "DRV008"},
            {"driver9",  "Tirupati Reddy",       "AP25-DL-2019-1009", "DRV009"},
            {"driver10", "Lakshmi Prasad",       "AP25-DL-2017-1010", "DRV010"},
        };

        List<User>   driverUsers   = new ArrayList<>();
        List<Driver> drivers       = new ArrayList<>();
        for (String[] d : driverData) {
            User u = save(User.builder().username(d[0]).email(d[0] + "@mbu.demo")
                    .passwordHash(enc).role(Role.ROLE_DRIVER).isActive(true).build());
            Driver drv = driverRepository.save(Driver.builder()
                    .user(u).driverId(d[3]).fullName(d[1])
                    .phone("0877-2XXXXXX").licenseNumber(d[2]).build());
            driverUsers.add(u);
            drivers.add(drv);
        }

        // ── Routes (10 — external, originating outside MBU Campus) ───────────
        // All routes terminate at MBU Campus; origins are towns in Tirupati region, AP.
        Route[] routes = {
            buildRoute("Tirupati Main Route",     "RTE-001", "Tirupati Bus Stand",     "MBU Campus",
                List.of("Tirupati Bus Stand","Renigunta Junction","Chandragiri Bypass","Yerpedu Cross","MBU Campus")),
            buildRoute("Renigunta Route",          "RTE-002", "Renigunta Railway Stn",  "MBU Campus",
                List.of("Renigunta Railway Stn","Renigunta Town","AP Dairy Cross","Tirupati Bypass","MBU Campus")),
            buildRoute("Chandragiri Route",        "RTE-003", "Chandragiri Fort Gate",  "MBU Campus",
                List.of("Chandragiri Fort Gate","Chandragiri Town","Puttur Bypass","Karakambadi","MBU Campus")),
            buildRoute("Srikalahasti Route",       "RTE-004", "Srikalahasti Temple",    "MBU Campus",
                List.of("Srikalahasti Temple","Srikalahasti Bus Stand","Punganur Road","Pileru Cross","MBU Campus")),
            buildRoute("Puttur Route",             "RTE-005", "Puttur Bus Stand",       "MBU Campus",
                List.of("Puttur Bus Stand","Puttur Town","Chandragiri Junction","Nagalapuram Road","MBU Campus")),
            buildRoute("Yerpedu Route",            "RTE-006", "Yerpedu Town",           "MBU Campus",
                List.of("Yerpedu Town","Yerpedu Cross","Industrial Area","Alipiri Gate","MBU Campus")),
            buildRoute("Pakala Route",             "RTE-007", "Pakala Junction",        "MBU Campus",
                List.of("Pakala Junction","Piler Road","Pileru","Satyavedu","MBU Campus")),
            buildRoute("Gudur Route",              "RTE-008", "Gudur Bus Stand",        "MBU Campus",
                List.of("Gudur Bus Stand","Nellore Bypass","Sullurpeta","Naidupeta","MBU Campus")),
            buildRoute("Naidupeta Route",          "RTE-009", "Naidupeta Town",         "MBU Campus",
                List.of("Naidupeta Town","Venkatagiri Road","Sullurpeta","Tada","MBU Campus")),
            buildRoute("Sullurpeta Route",         "RTE-010", "Sullurpeta Town",        "MBU Campus",
                List.of("Sullurpeta Town","Nellore Road","ISRO Township","Venkatachalam","MBU Campus")),
        };
        for (Route r : routes) routeRepository.save(r);

        // ── Buses (10) ────────────────────────────────────────────────────────
        String[][] busData = {
            // {busNumber, registration, collegeSerial, capacity, status}
            {"MBU-001","AP25-AB-1001","CS-01","52","ACTIVE"},
            {"MBU-002","AP25-AB-1002","CS-02","48","ON_ROUTE"},
            {"MBU-003","AP25-AB-1003","CS-03","52","ACTIVE"},
            {"MBU-004","AP25-AB-1004","CS-04","48","ACTIVE"},
            {"MBU-005","AP25-AB-1005","CS-05","52","ON_ROUTE"},
            {"MBU-006","AP25-AB-1006","CS-06","48","ACTIVE"},
            {"MBU-007","AP25-AB-1007","CS-07","52","MAINTENANCE"},
            {"MBU-008","AP25-AB-1008","CS-08","48","ACTIVE"},
            {"MBU-009","AP25-AB-1009","CS-09","52","ACTIVE"},
            {"MBU-010","AP25-AB-1010","CS-10","48","ACTIVE"},
        };
        List<Bus> buses = new ArrayList<>();
        for (String[] b : busData) {
            buses.add(busRepository.save(Bus.builder()
                    .busNumber(b[0]).registration(b[1]).collegeSerial(b[2])
                    .capacity(Integer.parseInt(b[3]))
                    .status(BusStatus.valueOf(b[4])).build()));
        }

        // ── Bus ↔ Route Assignments ───────────────────────────────────────────
        for (int i = 0; i < 10; i++) {
            busAssignmentRepository.save(BusAssignment.builder()
                    .bus(buses.get(i)).route(routes[i])
                    .effectiveFrom(LocalDate.now().minusDays(60)).isActive(true).build());
        }

        // ── Driver ↔ Bus Assignments ──────────────────────────────────────────
        for (int i = 0; i < 10; i++) {
            driverAssignmentRepository.save(DriverAssignment.builder()
                    .driver(drivers.get(i)).bus(buses.get(i))
                    .effectiveFrom(LocalDate.now().minusDays(60)).isActive(true).build());
        }

        // ── Students (50) ─────────────────────────────────────────────────────
        // 5 students per bus (buses 0–9)
        String[][] studentData = {
            // {username, studentId, fullName, dept, year}
            // Bus 0 (MBU-001, Tirupati Route)
            {"student1",  "STU001","Ravi Kumar",        "Computer Science",          "3rd Year"},
            {"student2",  "STU002","Priya Sharma",      "Electronics Engineering",   "2nd Year"},
            {"student3",  "STU003","Anil Reddy",        "Mechanical Engineering",    "1st Year"},
            {"student4",  "STU004","Sowmya Devi",       "Computer Science",          "4th Year"},
            {"student5",  "STU005","Karthik Nair",      "Civil Engineering",         "3rd Year"},
            // Bus 1 (MBU-002, Renigunta Route)
            {"student6",  "STU006","Pooja Verma",       "Computer Science",          "2nd Year"},
            {"student7",  "STU007","Naveen Babu",       "Electrical Engineering",    "1st Year"},
            {"student8",  "STU008","Divya Lakshmi",     "Computer Science",          "3rd Year"},
            {"student9",  "STU009","Rohit Singh",       "Mechanical Engineering",    "4th Year"},
            {"student10", "STU010","Aarti Mishra",      "Electronics Engineering",   "2nd Year"},
            // Bus 2 (MBU-003, Chandragiri Route)
            {"student11", "STU011","Suresh Babu",       "Civil Engineering",         "3rd Year"},
            {"student12", "STU012","Latha Kumari",      "Computer Science",          "1st Year"},
            {"student13", "STU013","Mahesh Goud",       "Electrical Engineering",    "2nd Year"},
            {"student14", "STU014","Rekha Nair",        "Computer Science",          "4th Year"},
            {"student15", "STU015","Vijay Shankar",     "Mechanical Engineering",    "3rd Year"},
            // Bus 3 (MBU-004, Srikalahasti Route)
            {"student16", "STU016","Aruna Reddy",       "Electronics Engineering",   "2nd Year"},
            {"student17", "STU017","Prakash Rao",       "Computer Science",          "1st Year"},
            {"student18", "STU018","Sunitha Devi",      "Civil Engineering",         "3rd Year"},
            {"student19", "STU019","Ganesh Naidu",      "Computer Science",          "4th Year"},
            {"student20", "STU020","Bharathi Kumari",   "Electrical Engineering",    "2nd Year"},
            // Bus 4 (MBU-005, Puttur Route)
            {"student21", "STU021","Chetan Kumar",      "Mechanical Engineering",    "1st Year"},
            {"student22", "STU022","Nithya Priya",      "Computer Science",          "3rd Year"},
            {"student23", "STU023","Rajesh Gupta",      "Electronics Engineering",   "2nd Year"},
            {"student24", "STU024","Kavitha Rani",      "Computer Science",          "4th Year"},
            {"student25", "STU025","Santhosh Babu",     "Civil Engineering",         "3rd Year"},
            // Bus 5 (MBU-006, Yerpedu Route)
            {"student26", "STU026","Meena Kumari",      "Computer Science",          "1st Year"},
            {"student27", "STU027","Harish Reddy",      "Electrical Engineering",    "2nd Year"},
            {"student28", "STU028","Padma Vathi",       "Computer Science",          "3rd Year"},
            {"student29", "STU029","Srinath Rao",       "Mechanical Engineering",    "4th Year"},
            {"student30", "STU030","Lalitha Devi",      "Electronics Engineering",   "2nd Year"},
            // Bus 6 (MBU-007 — MAINTENANCE, students retained but no active trip)
            {"student31", "STU031","Venkateswara Rao",  "Computer Science",          "1st Year"},
            {"student32", "STU032","Anusha Naidu",      "Civil Engineering",         "3rd Year"},
            {"student33", "STU033","Madhu Babu",        "Electrical Engineering",    "2nd Year"},
            {"student34", "STU034","Swathi Lakshmi",    "Computer Science",          "4th Year"},
            {"student35", "STU035","Nagesh Kumar",      "Mechanical Engineering",    "3rd Year"},
            // Bus 7 (MBU-008, Pakala Route)
            {"student36", "STU036","Hymavathi Devi",    "Computer Science",          "2nd Year"},
            {"student37", "STU037","Raju Varma",        "Electronics Engineering",   "1st Year"},
            {"student38", "STU038","Sudha Rani",        "Computer Science",          "3rd Year"},
            {"student39", "STU039","Venkat Ramana",     "Civil Engineering",         "4th Year"},
            {"student40", "STU040","Usha Kiran",        "Electrical Engineering",    "2nd Year"},
            // Bus 8 (MBU-009, Gudur Route)
            {"student41", "STU041","Bhaskar Rao",       "Mechanical Engineering",    "1st Year"},
            {"student42", "STU042","Annapurna Devi",    "Computer Science",          "3rd Year"},
            {"student43", "STU043","Dinesh Babu",       "Electronics Engineering",   "2nd Year"},
            {"student44", "STU044","Kamala Kumari",     "Computer Science",          "4th Year"},
            {"student45", "STU045","Surya Prakash",     "Civil Engineering",         "3rd Year"},
            // Bus 9 (MBU-010, Sullurpeta Route)
            {"student46", "STU046","Subba Rao",         "Electrical Engineering",    "2nd Year"},
            {"student47", "STU047","Nirmala Devi",      "Computer Science",          "1st Year"},
            {"student48", "STU048","Ramakrishna Rao",   "Mechanical Engineering",    "3rd Year"},
            {"student49", "STU049","Saroja Kumari",     "Computer Science",          "4th Year"},
            {"student50", "STU050","Hemanth Kumar",     "Electronics Engineering",   "2nd Year"},
        };

        List<Student> students = new ArrayList<>();
        List<User>    studentUsers = new ArrayList<>();
        for (String[] s : studentData) {
            User u = save(User.builder().username(s[0]).email(s[0] + "@mbu.demo")
                    .passwordHash(enc).role(Role.ROLE_STUDENT).isActive(true).build());
            Student stu = studentRepository.save(Student.builder()
                    .user(u).studentId(s[1]).fullName(s[2])
                    .phone("0877-2XXXXXX").department(s[3]).year(s[4]).build());
            studentUsers.add(u);
            students.add(stu);
        }

        // ── Student ↔ Bus Assignments (5 students per bus) ───────────────────
        for (int busIdx = 0; busIdx < 10; busIdx++) {
            Bus bus     = buses.get(busIdx);
            Route route = routes[busIdx];
            RouteStop firstStop = route.getStops().isEmpty() ? null : route.getStops().get(0);

            for (int stuOffset = 0; stuOffset < 5; stuOffset++) {
                int stuIdx = busIdx * 5 + stuOffset;
                studentBusAssignmentRepository.save(StudentBusAssignment.builder()
                        .student(students.get(stuIdx)).bus(bus)
                        .boardingStop(firstStop)
                        .effectiveFrom(LocalDate.now().minusDays(60)).isActive(true).build());
            }
        }

        // ── Trips ─────────────────────────────────────────────────────────────
        // Active trip on Bus 1 (MBU-002)
        Trip activeTrip = tripRepository.save(Trip.builder()
                .bus(buses.get(1)).route(routes[1]).driver(drivers.get(1))
                .scheduledDeparture(LocalDateTime.now().minusHours(1))
                .scheduledArrival(LocalDateTime.now().plusMinutes(35))
                .status(TripStatus.ON_ROUTE).build());

        // Active trip on Bus 4 (MBU-005)
        Trip activeTrip2 = tripRepository.save(Trip.builder()
                .bus(buses.get(4)).route(routes[4]).driver(drivers.get(4))
                .scheduledDeparture(LocalDateTime.now().minusMinutes(30))
                .scheduledArrival(LocalDateTime.now().plusMinutes(60))
                .status(TripStatus.ON_ROUTE).build());

        // Completed trip on Bus 0 (MBU-001)
        Trip completedTrip = tripRepository.save(Trip.builder()
                .bus(buses.get(0)).route(routes[0]).driver(drivers.get(0))
                .scheduledDeparture(LocalDateTime.now().minusHours(6))
                .actualDeparture(LocalDateTime.now().minusHours(6))
                .scheduledArrival(LocalDateTime.now().minusHours(5))
                .actualArrival(LocalDateTime.now().minusHours(4).minusMinutes(50))
                .status(TripStatus.COMPLETED).build());

        // Scheduled trips for tomorrow
        for (int i = 0; i < 8; i++) {
            if (i != 6) { // skip bus 6 (MAINTENANCE)
                tripRepository.save(Trip.builder()
                        .bus(buses.get(i)).route(routes[i]).driver(drivers.get(i))
                        .scheduledDeparture(LocalDateTime.now().plusDays(1).withHour(7).withMinute(30))
                        .scheduledArrival(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0))
                        .status(TripStatus.SCHEDULED).build());
            }
        }

        // ── QR Codes for first 10 students ────────────────────────────────────
        List<QRCode> qrCodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qrCodes.add(qrCodeRepository.save(QRCode.builder()
                    .student(students.get(i)).qrToken(UUID.randomUUID().toString())
                    .isActive(true).expiresAt(LocalDateTime.now().plusDays(365)).build()));
        }

        // ── Attendance ─────────────────────────────────────────────────────────
        attendanceRepository.save(Attendance.builder()
                .student(students.get(0)).trip(completedTrip).qrCode(qrCodes.get(0))
                .scannedAt(LocalDateTime.now().minusHours(5)).status(AttendanceStatus.PRESENT).build());
        attendanceRepository.save(Attendance.builder()
                .student(students.get(1)).trip(completedTrip).qrCode(qrCodes.get(1))
                .scannedAt(LocalDateTime.now().minusHours(5).plusMinutes(5)).status(AttendanceStatus.LATE).build());
        attendanceRepository.save(Attendance.builder()
                .student(students.get(2)).trip(completedTrip).qrCode(qrCodes.get(2))
                .scannedAt(LocalDateTime.now().minusHours(5).plusMinutes(2)).status(AttendanceStatus.PRESENT).build());
        attendanceRepository.save(Attendance.builder()
                .student(students.get(5)).trip(activeTrip).qrCode(qrCodes.get(5))
                .scannedAt(LocalDateTime.now().minusMinutes(55)).status(AttendanceStatus.PRESENT).build());

        // ── Complaints ─────────────────────────────────────────────────────────
        complaintRepository.save(Complaint.builder()
                .submittedBy(students.get(0).getId()).submitterType(SubmitterType.STUDENT)
                .category("Delay").description("DEMO DATA: Bus MBU-001 was delayed by 30 minutes on the Tirupati Main Route this morning.")
                .bus(buses.get(0)).currentStatus(ComplaintStatusEnum.IN_REVIEW).build());
        complaintRepository.save(Complaint.builder()
                .submittedBy(students.get(5).getId()).submitterType(SubmitterType.STUDENT)
                .category("Overcrowding").description("DEMO DATA: Bus MBU-002 on Renigunta Route was overcrowded yesterday evening.")
                .bus(buses.get(1)).currentStatus(ComplaintStatusEnum.OPEN).build());
        complaintRepository.save(Complaint.builder()
                .submittedBy(students.get(10).getId()).submitterType(SubmitterType.STUDENT)
                .category("Driver Behavior").description("DEMO DATA: Driver was over-speeding on the Chandragiri highway section.")
                .bus(buses.get(2)).currentStatus(ComplaintStatusEnum.ESCALATED).build());
        complaintRepository.save(Complaint.builder()
                .submittedBy(drivers.get(0).getId()).submitterType(SubmitterType.DRIVER)
                .category("Tyre Problem").description("DEMO DATA: Front left tyre of MBU-001 shows wear. Needs immediate inspection.")
                .bus(buses.get(0)).currentStatus(ComplaintStatusEnum.RESOLVED)
                .managementResponse("Tyre inspected and replaced. Bus cleared for service.").build());
        complaintRepository.save(Complaint.builder()
                .submittedBy(drivers.get(1).getId()).submitterType(SubmitterType.DRIVER)
                .category("Brake Problem").description("DEMO DATA: Brake pads on MBU-002 need replacement before next run.")
                .bus(buses.get(1)).currentStatus(ComplaintStatusEnum.IN_REVIEW).build());
        complaintRepository.save(Complaint.builder()
                .submittedBy(drivers.get(2).getId()).submitterType(SubmitterType.DRIVER)
                .category("Engine Issue").description("DEMO DATA: Engine overheating on extended Chandragiri route. Requires service check.")
                .bus(buses.get(2)).currentStatus(ComplaintStatusEnum.OPEN).build());

        // ── Maintenance Records ────────────────────────────────────────────────
        maintenanceRecordRepository.save(MaintenanceRecord.builder()
                .bus(buses.get(0)).component("Engine Service")
                .description("DEMO DATA: Routine 30,000 km engine oil change and filter replacement.")
                .serviceDate(LocalDate.now().minusDays(10))
                .nextServiceDate(LocalDate.now().plusDays(80))
                .status(MaintenanceStatus.COMPLETED).recordedBy(mgmt.getId()).build());
        maintenanceRecordRepository.save(MaintenanceRecord.builder()
                .bus(buses.get(6)).component("General Overhaul")
                .description("DEMO DATA: Full vehicle inspection — engine, brakes, tyres, AC unit. Bus offline.")
                .serviceDate(LocalDate.now().minusDays(3))
                .nextServiceDate(LocalDate.now().plusDays(7))
                .status(MaintenanceStatus.IN_PROGRESS).recordedBy(mgmt.getId()).build());
        maintenanceRecordRepository.save(MaintenanceRecord.builder()
                .bus(buses.get(1)).component("Tyre Rotation")
                .description("DEMO DATA: All four tyres rotated and pressure balanced.")
                .serviceDate(LocalDate.now().plusDays(5))
                .status(MaintenanceStatus.SCHEDULED).recordedBy(mgmt.getId()).build());
        maintenanceRecordRepository.save(MaintenanceRecord.builder()
                .bus(buses.get(3)).component("AC Service")
                .description("DEMO DATA: Air conditioning unit cleaned and refrigerant topped up.")
                .serviceDate(LocalDate.now().minusDays(20))
                .nextServiceDate(LocalDate.now().plusDays(160))
                .status(MaintenanceStatus.COMPLETED).recordedBy(mgmt.getId()).build());
        maintenanceRecordRepository.save(MaintenanceRecord.builder()
                .bus(buses.get(7)).component("Brake Pad Replacement")
                .description("DEMO DATA: All four wheel brake pads replaced. Brake fluid topped up.")
                .serviceDate(LocalDate.now().plusDays(2))
                .status(MaintenanceStatus.SCHEDULED).recordedBy(mgmt.getId()).build());

        // ── Emergency Contacts ─────────────────────────────────────────────────
        emergencyContactRepository.save(EmergencyContact.builder()
                .name("Transport Office").role("Transport Manager")
                .phone("0877-2XXXXXX").category(EmergencyCategory.TRANSPORT).isActive(true).build());
        emergencyContactRepository.save(EmergencyContact.builder()
                .name("Campus Security Control").role("Security Officer")
                .phone("0877-2XXXXXX").category(EmergencyCategory.CAMPUS).isActive(true).build());
        emergencyContactRepository.save(EmergencyContact.builder()
                .name("Campus Medical Centre").role("Medical Officer")
                .phone("0877-2XXXXXX").category(EmergencyCategory.MEDICAL).isActive(true).build());
        emergencyContactRepository.save(EmergencyContact.builder()
                .name("Tirupati Police Emergency").role("Police Control Room")
                .phone("100").category(EmergencyCategory.CAMPUS).isActive(true).build());
        emergencyContactRepository.save(EmergencyContact.builder()
                .name("MBU Helpline").role("Student Helpdesk")
                .phone("0877-2XXXXXX").category(EmergencyCategory.TRANSPORT).isActive(true).build());

        // ── Notifications ──────────────────────────────────────────────────────
        // Student 1 notifications
        notificationRepository.save(Notification.builder().user(studentUsers.get(0))
                .title("Bus Delay Notice").message("DEMO: MBU-001 will depart 15 minutes late today due to Tirupati traffic.")
                .isRead(false).build());
        notificationRepository.save(Notification.builder().user(studentUsers.get(0))
                .title("Complaint Update").message("DEMO: Your complaint about bus delay has been reviewed by management.")
                .isRead(true).build());
        notificationRepository.save(Notification.builder().user(studentUsers.get(0))
                .title("Route Change").message("DEMO: Tomorrow's route will bypass Chandragiri due to road work.")
                .isRead(false).build());
        // Student 2 notifications
        notificationRepository.save(Notification.builder().user(studentUsers.get(1))
                .title("Bus Arrival Alert").message("DEMO: Your bus MBU-002 will arrive at Renigunta in 10 minutes.")
                .isRead(false).build());
        notificationRepository.save(Notification.builder().user(studentUsers.get(1))
                .title("QR Code Renewed").message("DEMO: Your boarding QR code has been renewed for this semester.")
                .isRead(true).build());
        // Driver 1 notifications
        notificationRepository.save(Notification.builder().user(driverUsers.get(0))
                .title("Schedule Change").message("DEMO: Tomorrow's first trip (Tirupati Route) rescheduled to 07:15 AM.")
                .isRead(false).build());
        notificationRepository.save(Notification.builder().user(driverUsers.get(0))
                .title("Maintenance Due").message("DEMO: MBU-001 is due for tyre rotation in 5 days.")
                .isRead(false).build());
        // Driver 2 notifications
        notificationRepository.save(Notification.builder().user(driverUsers.get(1))
                .title("Complaint Filed").message("DEMO: A student complaint about overcrowding on your bus has been filed.")
                .isRead(false).build());
        // Management notifications
        notificationRepository.save(Notification.builder().user(uMgmt)
                .title("Bus Offline Alert").message("DEMO: MBU-007 is under maintenance and offline for this week.")
                .isRead(false).build());
        notificationRepository.save(Notification.builder().user(uMgmt)
                .title("New Complaint").message("DEMO: A student complaint about driver behavior on MBU-003 has been escalated.")
                .isRead(false).build());
        notificationRepository.save(Notification.builder().user(uMgmt)
                .title("Trip Summary").message("DEMO: 8 trips completed today. 2 trips delayed by >10 minutes.")
                .isRead(true).build());

        log.info("DataSeeder: DEMO data seeded — 10 buses, 10 routes, 10 drivers, 50 students.");
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    private Route buildRoute(String name, String code, String start, String end, List<String> stopNames) {
        Route route = Route.builder()
                .routeName(name).routeCode(code)
                .startPoint(start).endPoint(end).isActive(true).build();
        for (int i = 0; i < stopNames.size(); i++) {
            RouteStop stop = RouteStop.builder()
                    .route(route).stopName(stopNames.get(i))
                    .stopOrder(i + 1).build();
            route.getStops().add(stop);
        }
        return route;
    }
}

package com.mbu.routex.management.service;

import com.mbu.routex.attendance.repository.AttendanceRepository;
import com.mbu.routex.bus.repository.BusRepository;
import com.mbu.routex.complaint.repository.ComplaintRepository;
import com.mbu.routex.maintenance.repository.MaintenanceRecordRepository;
import com.mbu.routex.management.entity.Report;
import com.mbu.routex.management.repository.ReportRepository;
import com.mbu.routex.student.repository.StudentBusAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagementReportService {

    private final BusRepository busRepository;
    private final StudentBusAssignmentRepository studentBusAssignmentRepository;
    private final ComplaintRepository complaintRepository;
    private final MaintenanceRecordRepository maintenanceRecordRepository;
    private final AttendanceRepository attendanceRepository;
    private final ReportRepository reportRepository;

    public Map<String, Object> generateReport(String reportType, LocalDate startDate, LocalDate endDate) {
        Map<String, Object> reportData = new LinkedHashMap<>();
        reportData.put("reportType", reportType != null ? reportType : "FLEET_SUMMARY");
        reportData.put("generatedDate", LocalDate.now());
        reportData.put("startDate", startDate != null ? startDate : LocalDate.now().minusMonths(1));
        reportData.put("endDate", endDate != null ? endDate : LocalDate.now());

        long totalBuses = busRepository.count();
        long activeAssignments = studentBusAssignmentRepository.count();
        long totalComplaints = complaintRepository.count();
        long maintenanceCount = maintenanceRecordRepository.count();
        long attendanceCount = attendanceRepository.count();

        reportData.put("totalBuses", totalBuses);
        reportData.put("activeAssignments", activeAssignments);
        reportData.put("totalComplaints", totalComplaints);
        reportData.put("maintenanceCount", maintenanceCount);
        reportData.put("attendanceCount", attendanceCount);

        List<Map<String, String>> summaryRows = new ArrayList<>();
        summaryRows.add(Map.of("Metric", "Active Fleet Size", "Value", String.valueOf(totalBuses), "Status", "Optimal"));
        summaryRows.add(Map.of("Metric", "Students Registered on Transit", "Value", String.valueOf(activeAssignments), "Status", "Normal"));
        summaryRows.add(Map.of("Metric", "Total Grievances / Tickets", "Value", String.valueOf(totalComplaints), "Status", "Monitored"));
        summaryRows.add(Map.of("Metric", "Fleet Servicing Records", "Value", String.valueOf(maintenanceCount), "Status", "Up to Date"));
        summaryRows.add(Map.of("Metric", "Digital QR Boarding Scans", "Value", String.valueOf(attendanceCount), "Status", "Verified"));

        reportData.put("rows", summaryRows);
        return reportData;
    }

    public List<Report> getSavedReports() {
        return reportRepository.findAll();
    }
}

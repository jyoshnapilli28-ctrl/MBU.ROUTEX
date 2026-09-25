package com.mbu.routex.student.service;

import com.mbu.routex.attendance.entity.Attendance;
import com.mbu.routex.attendance.entity.QRCode;
import com.mbu.routex.attendance.repository.AttendanceRepository;
import com.mbu.routex.attendance.repository.QRCodeRepository;
import com.mbu.routex.common.exception.ResourceNotFoundException;
import com.mbu.routex.common.util.QRCodeUtil;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentAttendanceService {

    private final StudentRepository studentRepository;
    private final QRCodeRepository qrCodeRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public QRCode getOrCreateQrCode(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));

        return qrCodeRepository.findByStudentId(student.getId())
                .orElseGet(() -> {
                    QRCode newQr = QRCode.builder()
                            .student(student)
                            .qrToken("MBU-QR-" + student.getStudentId() + "-" + UUID.randomUUID())
                            .isActive(true)
                            .expiresAt(LocalDateTime.now().plusYears(1))
                            .build();
                    return qrCodeRepository.save(newQr);
                });
    }

    @Transactional(readOnly = true)
    public byte[] getQrCodeImage(Long userId) {
        QRCode qrCode = getOrCreateQrCode(userId);
        return QRCodeUtil.generateQRCodeImage(qrCode.getQrToken(), 260, 260);
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceHistory(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));
        return attendanceRepository.findByStudentIdOrderByScannedAtDesc(student.getId());
    }

    @Transactional(readOnly = true)
    public Student getStudent(Long userId) {
        return studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user ID: " + userId));
    }
}

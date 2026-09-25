package com.mbu.routex.attendance.repository;

import com.mbu.routex.attendance.entity.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    Optional<QRCode> findByStudentId(Long studentId);
    Optional<QRCode> findByStudentIdAndIsActiveTrue(Long studentId);
    Optional<QRCode> findByQrToken(String qrToken);
}

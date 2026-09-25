package com.mbu.routex.emergency.repository;

import com.mbu.routex.emergency.entity.EmergencyContact;
import com.mbu.routex.emergency.entity.EmergencyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    List<EmergencyContact> findByIsActiveTrueOrderByCategoryAsc();
    List<EmergencyContact> findByCategoryAndIsActiveTrue(EmergencyCategory category);
}

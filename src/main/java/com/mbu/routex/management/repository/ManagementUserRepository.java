package com.mbu.routex.management.repository;

import com.mbu.routex.management.entity.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ManagementUserRepository extends JpaRepository<ManagementUser, Long> {
    Optional<ManagementUser> findByUserId(Long userId);
}

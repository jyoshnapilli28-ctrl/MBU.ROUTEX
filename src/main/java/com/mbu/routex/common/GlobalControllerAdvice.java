package com.mbu.routex.common;

import com.mbu.routex.driver.entity.Driver;
import com.mbu.routex.driver.repository.DriverRepository;
import com.mbu.routex.notification.repository.NotificationRepository;
import com.mbu.routex.student.entity.Student;
import com.mbu.routex.student.repository.StudentRepository;
import com.mbu.routex.user.entity.User;
import com.mbu.routex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * GlobalControllerAdvice — injects common model attributes
 * (currentUsername, unreadCount, displayId, displayRole) into ALL views.
 *
 * These attributes are available in every Thymeleaf template,
 * including fragments like taskbar.html.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final NotificationRepository notificationRepository;

    /** Spring Security username of the logged-in user */
    @ModelAttribute("currentUsername")
    public String currentUsername(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails != null ? userDetails.getUsername() : null;
    }

    /** Count of unread notifications for the current user */
    @ModelAttribute("unreadCount")
    public long unreadCount(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return 0;
        return userRepository.findByUsername(userDetails.getUsername())
                .map(u -> notificationRepository.countByUserIdAndIsReadFalse(u.getId()))
                .orElse(0L);
    }

    /**
     * Role-specific display ID (student ID, driver ID, or "MGMT") shown in the
     * top-right corner of the taskbar.
     */
    @ModelAttribute("displayId")
    public String displayId(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return null;
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null || user.getRole() == null) return null;

        return switch (user.getRole()) {
            case ROLE_STUDENT -> studentRepository.findByUserId(user.getId())
                    .map(Student::getStudentId).orElse(userDetails.getUsername());
            case ROLE_DRIVER  -> driverRepository.findByUserId(user.getId())
                    .map(Driver::getDriverId).orElse(userDetails.getUsername());
            case ROLE_MANAGEMENT -> "MGMT-ADM";
        };
    }

    /**
     * Human-readable role label ("Student", "Driver", "Management") shown
     * above the display ID in the taskbar.
     */
    @ModelAttribute("displayRole")
    public String displayRole(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return null;
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null || user.getRole() == null) return null;

        return switch (user.getRole()) {
            case ROLE_STUDENT    -> "Student";
            case ROLE_DRIVER     -> "Driver";
            case ROLE_MANAGEMENT -> "Management";
        };
    }
}

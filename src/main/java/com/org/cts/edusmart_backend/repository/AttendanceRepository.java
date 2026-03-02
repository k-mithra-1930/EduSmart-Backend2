// src/main/java/com/org/cts/edusmart_backend/repository/AttendanceRepository.java
package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByCourseIdAndDate(Long courseId, java.time.LocalDate date);
}
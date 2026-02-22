package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Enrollment;
import com.org.cts.edusmart_backend.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStatus(EnrollmentStatus status);
    List<Enrollment> findByCourse_Instructor_EmailAndStatus(String email, EnrollmentStatus status);
    List<Enrollment> findByStudentId(Long studentId);
}
package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}
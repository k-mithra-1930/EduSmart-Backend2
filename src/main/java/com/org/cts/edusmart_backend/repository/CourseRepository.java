package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
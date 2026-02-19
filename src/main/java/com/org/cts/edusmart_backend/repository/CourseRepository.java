package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(CourseStatus status);


}
package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.CourseStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.id NOT IN " +
            "(SELECT e.course.id FROM Enrollment e WHERE e.student.id = :studentId)")
    List<Course> findCoursesNotEnrolledByStudent(@Param("studentId") Long studentId);

    List<Course> findByStatus(CourseStatus status);


}
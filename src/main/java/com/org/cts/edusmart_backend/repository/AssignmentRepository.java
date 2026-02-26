package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourseId(Long courseId);

    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseId " +
    "AND NOT EXISTS (SELECT s FROM AssignmentSubmission s " +
    "WHERE s.assignmentId = a.id AND s.studentId = :studentId)")
    List<Assignment> findUnsolvedAssignments(@Param("courseId") Long courseId,
                                             @Param("studentId") Long studentId);
}
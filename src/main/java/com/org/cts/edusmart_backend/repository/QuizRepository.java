package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourseId(Long courseId);

    @Query("SELECT q from Quiz q where q.course.id = :courseId "+
    "AND NOT EXISTS (SELECT s FROM QuizSubmission s "+
    "WHERE s.quiz = q AND s.student.id = :studentId)")
    List<Quiz> findUnsolvedQuizzesByCourse(Long courseId, Long studentId);
}
package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByQuizId(Long quizId);
    List<QuizSubmission> findByStudentId(Long studentId);


}
package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.QuizSubmission;
import com.org.cts.edusmart_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByQuizId(Long quizId);
    List<QuizSubmission> findByStudentId(Long studentId);

    List<QuizSubmission> findByStudentAndQuiz_Course(User student, Course course);


}
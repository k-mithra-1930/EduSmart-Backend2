package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.ProgressDTO;
import com.org.cts.edusmart_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    @Autowired private AssignmentRepository assignmentRepository;
    @Autowired private QuizRepository quizRepository;
    @Autowired private AssignmentSubmissionRepository assignmentSubmissionRepository;
    @Autowired private QuizSubmissionRepository quizSubmissionRepository;

    public ProgressDTO calculateProgress(Long studentId, Long courseId) {
        // 1. Get total count of tasks in the course
        long totalAssignments = assignmentRepository.findByCourseId(courseId).size();
        long totalQuizzes = quizRepository.findByCourseId(courseId).size();
        long totalTasks = totalAssignments + totalQuizzes;

        if (totalTasks == 0) {
            ProgressDTO zeroProgress = new ProgressDTO();
            zeroProgress.setPercentage(0.0);
            return zeroProgress;
        }

        // 2. Count completed assignments (submissions for this specific student and course)
        long completedAssignments = assignmentSubmissionRepository.findAll().stream()
                .filter(s -> s.getStudentId().equals(studentId) && s.getCourseId().equals(courseId))
                .count();

        // 3. Count completed quizzes
        long completedQuizzes = quizSubmissionRepository.findByStudentId(studentId).stream()
                .filter(s -> s.getQuiz().getCourse().getId().equals(courseId))
                .count();

        long completedTasks = completedAssignments + completedQuizzes;

        // 4. Calculate Percentage
        double percentage = ((double) completedTasks / totalTasks) * 100;

        ProgressDTO dto = new ProgressDTO();
        dto.setPercentage(Math.round(percentage * 100.0) / 100.0); // Round to 2 decimal places
        dto.setTotalTasks(totalTasks);
        dto.setCompletedTasks(completedTasks);

        return dto;
    }
}
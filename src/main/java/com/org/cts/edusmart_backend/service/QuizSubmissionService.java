package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.QuizSubmissionDTO;
import com.org.cts.edusmart_backend.dto.QuizSubmissionResponseDTO;
import com.org.cts.edusmart_backend.dto.SubmittedAnswerDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizSubmissionService {

    @Autowired private QuizSubmissionRepository submissionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private QuizRepository quizRepository;

    public List<QuizSubmission> getSubmissionsByStudent(Long quizId) {
        return submissionRepository.findByQuizId(quizId);
    }

    public QuizSubmissionResponseDTO submitQuiz(QuizSubmissionDTO dto) {
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        QuizSubmission submission = new QuizSubmission();
        submission.setStudent(student);
        submission.setQuiz(quiz);
        submission.setSubmissionDate(LocalDateTime.now());

        // Calculate score and map answers
        int score = 0;
        for (var ansDto : dto.getAnswers()) {
            if (ansDto.getAnswer().equalsIgnoreCase(ansDto.getSelectedAnswer())) {
                score++;
            }
        }
        submission.setScore(score);

        submission.setSubmittedAnswers(dto.getAnswers().stream().map(ansDto -> {
            SubmittedAnswer sa = new SubmittedAnswer();
            sa.setQuestionTitle(ansDto.getTitle());
            sa.setOptions(ansDto.getOptions());
            sa.setCorrectAnswer(ansDto.getAnswer());
            sa.setSelectedAnswer(ansDto.getSelectedAnswer());
            sa.setSubmission(submission);
            return sa;
        }).collect(Collectors.toList()));
        submissionRepository.save(submission);
        QuizSubmission savedSubmission = submissionRepository.save(submission);

        // Map Entity to the readable Response DTO
        QuizSubmissionResponseDTO response = new QuizSubmissionResponseDTO();
        response.setSubmissionId(savedSubmission.getId());
        response.setQuizId(quiz.getId());
        response.setQuizTitle(quiz.getQuestionTitle());
        response.setStudentId(student.getId());
        response.setStudentName(student.getName());
        response.setScore(savedSubmission.getScore());
        response.setSubmissionDate(savedSubmission.getSubmissionDate());

        response.setAnswers(savedSubmission.getSubmittedAnswers().stream().map(sa -> {
            SubmittedAnswerDTO adto = new SubmittedAnswerDTO();
            adto.setQuestionTitle(sa.getQuestionTitle());
            adto.setSelectedAnswer(sa.getSelectedAnswer());
            adto.setCorrectAnswer(sa.getCorrectAnswer());
            adto.setCorrect(sa.getCorrectAnswer().equalsIgnoreCase(sa.getSelectedAnswer()));
            return adto;
        }).collect(Collectors.toList()));

        return response;
    }
}
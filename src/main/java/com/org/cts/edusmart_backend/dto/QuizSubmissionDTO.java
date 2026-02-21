package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuizSubmissionDTO {
    private Long courseId;
    private Long quizId;
    private Long studentId;
    private String questionTitle; // Quiz Title
    private String description;   // Quiz Description
    private List<AnswerDTO> answers;
}
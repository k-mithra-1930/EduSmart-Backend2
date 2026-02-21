package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizSubmissionResponseDTO {
    private Long submissionId;
    private Long quizId;
    private String quizTitle;
    private Long studentId;
    private String studentName;
    private Integer score;
    private LocalDateTime submissionDate;
    private List<SubmittedAnswerDTO> answers;
}


package com.org.cts.edusmart_backend.dto;

import lombok.Data;

@Data
public class SubmittedAnswerDTO {
    private String questionTitle;
    private String selectedAnswer;
    private String correctAnswer;
    private boolean isCorrect;
}

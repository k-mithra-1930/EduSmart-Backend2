package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class AnswerDTO {
    private String title;
    private List<String> options;
    private String answer; // Correct answer
    private String selectedAnswer;
}
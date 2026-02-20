package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuizDTO {
    private String questionTitle;
    private String description;
    private List<QuestionDTO> questions;
}
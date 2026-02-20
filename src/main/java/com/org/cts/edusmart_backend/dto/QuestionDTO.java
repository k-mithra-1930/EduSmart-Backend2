package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {
    private String title;
    private List<String> options;
    private String answer;
}
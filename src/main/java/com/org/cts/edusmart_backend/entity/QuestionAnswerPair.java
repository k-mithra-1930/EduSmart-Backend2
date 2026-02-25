package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class QuestionAnswerPair {
    private String question;
    private String answer;
}
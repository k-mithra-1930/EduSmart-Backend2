package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "submitted_answers")
@Data
public class SubmittedAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionTitle;

    @ElementCollection
    private List<String> options;

    private String correctAnswer;
    private String selectedAnswer;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private QuizSubmission submission;
}
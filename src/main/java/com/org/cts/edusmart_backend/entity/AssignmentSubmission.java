package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assignment_submissions")
@Data
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;
    private String assignmentTitle;
    private Long courseId;
    private LocalDate endDate;
    private LocalDate submittedDate;
    private Long studentId;

    @ElementCollection
    @CollectionTable(name = "submission_answers", joinColumns = @JoinColumn(name = "submission_id"))
    private List<QuestionAnswerPair> questionsAnswerPair;
}
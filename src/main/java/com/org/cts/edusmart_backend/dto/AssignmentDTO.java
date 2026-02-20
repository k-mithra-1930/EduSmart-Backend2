package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AssignmentDTO {
    private Long courseId;
    private Long instructorId; // Use ID instead of relying on Auth email
    private LocalDate endDate;
    private List<String> assignmentQuestions;
    private String title;
}
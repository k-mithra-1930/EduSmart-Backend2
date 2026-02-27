package com.org.cts.edusmart_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentReportDTO {
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private double progressPercentage;
    private double averageQuizScore;
    private double averageAssignmentScore;
    private Long totalQuizzesTaken;
    private Long totalAssignmentsSubmitted;
}

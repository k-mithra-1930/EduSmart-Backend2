package com.org.cts.edusmart_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private Long id; // Enrollment ID
    private Long studentId;
    private String studentName;
    private String courseName;
    private LocalDate enrolledDate;
    private String status; // You can derive this or add a field to your entity
}
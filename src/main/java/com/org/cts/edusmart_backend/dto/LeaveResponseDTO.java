package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LeaveResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private String reason;
    private LocalDate leaveDate;
}
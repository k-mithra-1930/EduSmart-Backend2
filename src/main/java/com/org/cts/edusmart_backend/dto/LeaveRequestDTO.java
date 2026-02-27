package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LeaveRequestDTO {
    private Long studentId;
    private LocalDate leaveDate;
    private String reason; // This will receive the value from the frontend dropdown
}
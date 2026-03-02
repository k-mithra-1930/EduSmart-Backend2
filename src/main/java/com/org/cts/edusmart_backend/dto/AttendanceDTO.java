// src/main/java/com/org/cts/edusmart_backend/dto/AttendanceRequestDTO.java
package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceDTO {
    private Long studentId;
    private String studentName;
    private Long courseId;
    private Long instructorId;
    private LocalDate date;
    private boolean present;
}
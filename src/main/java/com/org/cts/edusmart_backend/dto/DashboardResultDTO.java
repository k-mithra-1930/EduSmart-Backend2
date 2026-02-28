package com.org.cts.edusmart_backend.dto;

import lombok.Data;

@Data
public class DashboardResultDTO {
    private Long total_users;
    private Long total_courses;
    private Long total_enrollments;
    private Long assessments;
}

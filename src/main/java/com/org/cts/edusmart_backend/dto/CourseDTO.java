package com.org.cts.edusmart_backend.dto;

import com.org.cts.edusmart_backend.entity.CourseStatus;
import lombok.Data;

@Data
public class CourseDTO {
    private String name;
    private String description;
    private CourseStatus status;
    private Long instructorId;

}

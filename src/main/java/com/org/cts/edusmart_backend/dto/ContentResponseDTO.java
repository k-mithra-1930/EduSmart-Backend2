package com.org.cts.edusmart_backend.dto;

import lombok.Data;

@Data
public class ContentResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Long courseId;
}

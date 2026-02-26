package com.org.cts.edusmart_backend.dto;

import lombok.Data;

@Data
public class ProgressDTO {
    private double percentage;
    private long totalTasks;
    private long completedTasks;
}
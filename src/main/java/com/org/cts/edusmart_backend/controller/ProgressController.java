package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.ProgressDTO;
import com.org.cts.edusmart_backend.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ProgressDTO> getProgress(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(progressService.calculateProgress(studentId, courseId));
    }
}
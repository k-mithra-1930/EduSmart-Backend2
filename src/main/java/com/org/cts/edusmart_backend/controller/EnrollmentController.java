package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.EnrollmentRequestDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/request")
    public ResponseEntity<?> requestEnrollment(@RequestBody EnrollmentRequestDTO dto) {
        try {
            return ResponseEntity.ok(enrollmentService.requestEnrollment(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> processRequest(@PathVariable Long id, @RequestParam EnrollmentStatus status) {
        try {
            return ResponseEntity.ok(enrollmentService.updateStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
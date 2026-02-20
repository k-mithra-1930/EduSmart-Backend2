package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.entity.Enrollment;
import com.org.cts.edusmart_backend.entity.EnrollmentStatus;
import com.org.cts.edusmart_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor/enrollments")
public class InstructorEnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // 1. Get all pending enrollment requests for the logged-in instructor
    @GetMapping("/pending")
    public ResponseEntity<List<Enrollment>> getMyPendingRequests(Authentication authentication) {
        String email = authentication.getName(); // Gets email from JWT token
        return ResponseEntity.ok(enrollmentService.getInstructorPendingRequests(email));
    }

    // 2. Accept or Reject a specific enrollment
    @PutMapping("/{id}/respond")
    public ResponseEntity<?> respondToRequest(
            @PathVariable Long id,
            @RequestParam EnrollmentStatus status) {
        try {
            Enrollment updated = enrollmentService.updateStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
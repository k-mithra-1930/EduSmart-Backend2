package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.AssignmentScoreDTO;
import com.org.cts.edusmart_backend.dto.SubmissionDTO;
import com.org.cts.edusmart_backend.entity.AssignmentSubmission;
import com.org.cts.edusmart_backend.service.AssignmentSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments/submit")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentSubmissionController {

    @Autowired
    private AssignmentSubmissionService service;

    @PostMapping
    public ResponseEntity<AssignmentSubmission> submitAssignment(@RequestBody SubmissionDTO submissionDTO) {
        return ResponseEntity.ok(service.saveSubmission(submissionDTO));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<List<AssignmentSubmission>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(service.getSubmissionsByAssignmentId(assignmentId));
    }

    @PutMapping("/give_score")
    public ResponseEntity<?> giveScoretoAssignment(@RequestBody AssignmentScoreDTO dto) {
        return ResponseEntity.ok(service.giveScoretoSubmission(dto));
    }
}
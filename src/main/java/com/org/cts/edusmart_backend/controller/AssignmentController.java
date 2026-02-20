package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.AssignmentDTO;
import com.org.cts.edusmart_backend.entity.Assignment;
import com.org.cts.edusmart_backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentDTO dto) {
        try {
            return ResponseEntity.ok(assignmentService.createAssignment(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourseId(courseId));
    }
}
package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.AssignmentDTO;
import com.org.cts.edusmart_backend.entity.Assignment;
import com.org.cts.edusmart_backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/course/{courseId}/delete/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long courseId, @PathVariable Long assignmentId) {
        try {
            assignmentService.deleteAssignment(assignmentId);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<?> getAssignmentQuestions(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(assignmentService.getById(assignmentId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/unsolved")
    public ResponseEntity<List<Assignment>> getUnsolvedAssignments(
            @RequestParam Long courseId,
            @RequestParam Long studentId
    ) {
        List<Assignment> unsolved = assignmentService.getUnsolvedAssignments(courseId,studentId);

        if(unsolved.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unsolved);
    }

}
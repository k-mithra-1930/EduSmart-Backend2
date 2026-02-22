package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.EnrollmentRequestDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long studentId) {
        try {
            List<Course> courses = enrollmentService.getCoursesByStudentId(studentId);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/request")
    public ResponseEntity<?> requestEnrollment(@RequestBody EnrollmentRequestDTO dto) {
        try {
            return ResponseEntity.ok(enrollmentService.requestEnrollment(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
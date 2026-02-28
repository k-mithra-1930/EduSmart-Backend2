package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.EnrollmentRequestDTO;
import com.org.cts.edusmart_backend.dto.EnrollmentResponseDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<Map<String, Object>>> getEnrolledStudents(@PathVariable Long courseId) {
        try {
            List<User> students = enrollmentService.getUniqueStudentsByCourseId(courseId);

            List<Map<String, Object>> response = students.stream().map(student -> {
                Map<String, Object> details = new HashMap<>();
                details.put("studentId", student.getId());
                details.put("name", student.getName());
                return details;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(response);
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

    @GetMapping("/all")
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

}
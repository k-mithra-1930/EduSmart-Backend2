// src/main/java/com/org/cts/edusmart_backend/controller/AttendanceController.java
package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.AttendanceDTO;
import com.org.cts.edusmart_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<AttendanceDTO>> getEnrolledStudents(@PathVariable Long courseId) {
        return ResponseEntity.ok(attendanceService.getStudentsForMarking(courseId));
    }

    // Updated to accept a List of DTOs for bulk marking
    @PostMapping("/mark-bulk")
    public ResponseEntity<?> markBulkAttendance(@RequestBody List<AttendanceDTO> dtos) {
        try {
            attendanceService.markBulkAttendance(dtos);
            return ResponseEntity.ok("Attendance recorded successfully for " + dtos.size() + " students.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // src/main/java/com/org/cts/edusmart_backend/controller/AttendanceController.java

    @GetMapping("/course/{courseId}/view")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceRecords(
            @PathVariable Long courseId,
            @RequestParam("date") String dateStr) {
        try {
            // Parse the date string from the request parameter
            java.time.LocalDate date = java.time.LocalDate.parse(dateStr);

            // Retrieve and return the list of records
            List<AttendanceDTO> records = attendanceService.getAttendanceByCourseAndDate(courseId, date);
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
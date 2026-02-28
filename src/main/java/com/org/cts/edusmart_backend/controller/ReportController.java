package com.org.cts.edusmart_backend.controller;


import com.org.cts.edusmart_backend.dto.StudentReportDTO;
import com.org.cts.edusmart_backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/all-students")
    public ResponseEntity<List<StudentReportDTO>> getAllReports() {
        List<StudentReportDTO> reports = reportService.getAllStudentsReports();
        if(reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData() {
        try {
            return ResponseEntity.ok(reportService.getDashboardData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

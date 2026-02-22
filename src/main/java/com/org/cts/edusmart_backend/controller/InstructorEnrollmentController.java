package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.entity.Enrollment;
import com.org.cts.edusmart_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor/enrollments")
public class InstructorEnrollmentController {
    // 2. Accept or Reject a specific enrollment
}
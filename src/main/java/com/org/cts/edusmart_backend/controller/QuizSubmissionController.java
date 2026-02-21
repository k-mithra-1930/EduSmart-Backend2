package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.QuizSubmissionDTO;
import com.org.cts.edusmart_backend.service.QuizSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes/submit")
public class QuizSubmissionController {

    @Autowired
    private QuizSubmissionService submissionService;

    @PostMapping
    public ResponseEntity<?> submitQuiz(@RequestBody QuizSubmissionDTO dto) {
        try {
            return ResponseEntity.ok(submissionService.submitQuiz(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
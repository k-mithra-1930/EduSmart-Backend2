package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.QuizDTO;
import com.org.cts.edusmart_backend.entity.Quiz;
import com.org.cts.edusmart_backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/course/{courseId}/unsolved")
    public ResponseEntity<List<Quiz>> getUnsolvedQuizzes(
            @PathVariable Long courseId,
            @RequestParam Long studentId
    ) {
        List<Quiz> unsolvedQuizzes = quizService.getUnsolvedQuizzes(courseId,studentId);
        return ResponseEntity.ok(unsolvedQuizzes);
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> createQuiz(@PathVariable Long courseId, @RequestBody QuizDTO quizDto) {
        try {
            return ResponseEntity.ok(quizService.addQuizToCourse(courseId, quizDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getQuizzes(@PathVariable Long courseId) {
        return ResponseEntity.ok(quizService.getQuizzesByCourse(courseId));
    }

    @DeleteMapping("/course/{courseId}/delete/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long courseId, @PathVariable Long quizId) {
        try {
            quizService.deleteQuiz(quizId);
            return ResponseEntity.ok("Quiz and all its questions deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
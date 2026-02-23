package com.org.cts.edusmart_backend.controller;


import com.org.cts.edusmart_backend.dto.ContentDTO;
import com.org.cts.edusmart_backend.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public ResponseEntity<?> getAllContent() {
        return ResponseEntity.ok(contentService.getAll());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getContentByCourseId(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(contentService.getByCourseId(courseId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContent(@RequestBody ContentDTO dto) {
        try {
            return ResponseEntity.ok(contentService.save(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable Long id) {
        try {
            contentService.delete(id);
            return ResponseEntity.ok("Content deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

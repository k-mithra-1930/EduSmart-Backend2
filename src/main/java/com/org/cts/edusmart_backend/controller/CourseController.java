package com.org.cts.edusmart_backend.controller;



import com.org.cts.edusmart_backend.dto.CourseDTO;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.CourseStatus;
import com.org.cts.edusmart_backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

   @Autowired
    private CourseService courseService;

   @GetMapping()
    public List<Course> Getcourses() {
       return courseService.getAll();
   }

   @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody CourseDTO dto) {
       try{
           return ResponseEntity.ok(courseService.save(dto));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
   }



   @PutMapping("/update/{id}/{status}")
    public Course updateCourse(@RequestParam Long id,@RequestParam CourseStatus status) {
       return courseService.update(status,id);
   }

   @DeleteMapping("/{id}")
    public void delete(Long id){
       courseService.delete(id);
   }

   @GetMapping("/{status}")
    public ResponseEntity<List<Course>> getCourseByStatus(@PathVariable CourseStatus status) {
       List<Course> courses = courseService.getcoursebystatus(status);
       return ResponseEntity.ok(courses);
   }

}

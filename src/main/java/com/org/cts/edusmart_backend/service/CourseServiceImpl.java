// File: src/main/java/com/org/cts/edusmart_backend/service/CourseServiceImpl.java
package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.CourseDTO;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.CourseStatus;
import com.org.cts.edusmart_backend.entity.User;
import com.org.cts.edusmart_backend.repository.CourseRepository;
import com.org.cts.edusmart_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository; // Added to find the instructor

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(CourseDTO dto) {
        // Fetch the instructor (User) from the database
        User instructor = userRepository.findById(dto.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found with Id: " + dto.getInstructorId()));

        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        course.setInstructor(instructor); // Now 'instructor' is defined

        return courseRepository.save(course);
    }



    @Override
    public Course update(CourseStatus status, Long id) {
        return courseRepository.findById(id).map(existing -> {
            existing.setStatus(status);
            return courseRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Course with ID " + id + " not found."));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getcoursebystatus(CourseStatus status) {
        return courseRepository.findByStatus(status);
    }
}
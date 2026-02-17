package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.CourseDTO;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.CourseStatus;
import com.org.cts.edusmart_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        return courseRepository.save(course);
    }

    @Override
    public Course update(CourseDTO dto,Long id) {
        return courseRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDescription(dto.getDescription());
            existing.setStatus(dto.getStatus());
            return courseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Course with ID "+id+" not found."));

    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }


}

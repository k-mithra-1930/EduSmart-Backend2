package com.org.cts.edusmart_backend.service;


import com.org.cts.edusmart_backend.dto.CourseDTO;
import com.org.cts.edusmart_backend.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    public List<Course> getAll();

    public Course save(CourseDTO course);
    public Course update(CourseDTO course,Long id);
    public void delete(Long id);

}

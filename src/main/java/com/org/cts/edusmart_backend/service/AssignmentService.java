package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.AssignmentDTO;
import com.org.cts.edusmart_backend.entity.Assignment;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.User;
import com.org.cts.edusmart_backend.repository.AssignmentRepository;
import com.org.cts.edusmart_backend.repository.CourseRepository;
import com.org.cts.edusmart_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Assignment createAssignment(AssignmentDTO dto) {
        // 1. Validate question limit (Max 10)
        if (dto.getAssignmentQuestions() != null && dto.getAssignmentQuestions().size() > 10) {
            throw new RuntimeException("An assignment cannot have more than 10 questions.");
        }

        // 2. Fetch Course
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + dto.getCourseId()));

        // 3. Fetch Instructor by ID instead of Email
        User instructor = userRepository.findById(dto.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + dto.getInstructorId()));

        Assignment assignment = new Assignment();
        assignment.setEndDate(dto.getEndDate());
        assignment.setTitle(dto.getTitle());
        assignment.setQuestions(dto.getAssignmentQuestions());
        assignment.setCourse(course);
        assignment.setInstructor(instructor);

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByCourseId(Long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    public void deleteAssignment(Long assignmentId) {
        if (!assignmentRepository.existsById(assignmentId)) {
            throw new RuntimeException("Assignment not found with ID: " + assignmentId);
        }
        assignmentRepository.deleteById(assignmentId);
    }
    public Assignment getById(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + assignmentId));

        return assignment;
    }

}
package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.EnrollmentRequestDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment requestEnrollment(EnrollmentRequestDTO dto) {
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.PENDING);

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getInstructorPendingRequests(String instructorEmail) {
        return enrollmentRepository.findByCourse_Instructor_EmailAndStatus(instructorEmail, EnrollmentStatus.PENDING);
    }

    public Enrollment updateStatus(Long enrollmentId, EnrollmentStatus status) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setStatus(status);
        return enrollmentRepository.save(enrollment);
    }

    public List<Course> getCoursesByStudentId(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

}
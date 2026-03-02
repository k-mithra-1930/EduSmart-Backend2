// src/main/java/com/org/cts/edusmart_backend/service/AttendanceService.java
package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.AttendanceDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnrollmentService enrollmentService;

    // New method to handle a list of students for bulk marking
    @Transactional
    public void markBulkAttendance(List<AttendanceDTO> dtos) {
        List<Attendance> attendanceList = dtos.stream().map(dto -> {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student ID " + dto.getStudentId() + " not found"));
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course ID " + dto.getCourseId() + " not found"));
            User instructor = userRepository.findById(dto.getInstructorId())
                    .orElseThrow(() -> new RuntimeException("Instructor ID " + dto.getInstructorId() + " not found"));

            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setCourse(course);
            attendance.setInstructor(instructor);
            attendance.setDate(dto.getDate());
            attendance.setPresent(dto.isPresent());
            return attendance;
        }).collect(Collectors.toList());

        attendanceRepository.saveAll(attendanceList);
    }

    public List<AttendanceDTO> getStudentsForMarking(Long courseId) {
        List<User> students = enrollmentService.getUniqueStudentsByCourseId(courseId);

        return students.stream().map(student -> {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setStudentId(student.getId());
            dto.setStudentName(student.getName());
            dto.setCourseId(courseId);
            dto.setPresent(false); // Default to absent for the list view
            return dto;
        }).collect(Collectors.toList());
    }
    // src/main/java/com/org/cts/edusmart_backend/service/AttendanceService.java

    public List<AttendanceDTO> getAttendanceByCourseAndDate(Long courseId, java.time.LocalDate date) {
        // Fetch records from the repository
        List<Attendance> records = attendanceRepository.findByCourseIdAndDate(courseId, date);

        // Map entities to DTOs for the response
        return records.stream().map(record -> {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setStudentId(record.getStudent().getId());
            dto.setStudentName(record.getStudent().getName()); // Include name for display
            dto.setCourseId(record.getCourse().getId());
            dto.setInstructorId(record.getInstructor().getId());
            dto.setDate(record.getDate());
            dto.setPresent(record.isPresent());
            return dto;
        }).collect(Collectors.toList());
    }
}
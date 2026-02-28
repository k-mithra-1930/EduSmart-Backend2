package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.DashboardResultDTO;
import com.org.cts.edusmart_backend.dto.StudentReportDTO;
import com.org.cts.edusmart_backend.entity.*;
import com.org.cts.edusmart_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;


    public DashboardResultDTO getDashboardData() {
        long studentCount = userRepository.countByRole("STUDENT");
        long enrollmentCount = enrollmentRepository.count();
        long quizCount = quizRepository.count();
        long assignmentCount = assignmentRepository.count();
        long assessmentCount = quizCount + assignmentCount;
        long courseCount = courseRepository.countByStatus(CourseStatus.APPROVED);

        DashboardResultDTO dto = new DashboardResultDTO();
        dto.setTotal_users(studentCount);
        dto.setTotal_enrollments(enrollmentCount);
        dto.setAssessments(assessmentCount);
        dto.setTotal_courses(courseCount);

        return dto;

    }

    public List<StudentReportDTO> getAllStudentsReports() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return enrollments.stream().map(enrollment -> {
            User student = enrollment.getStudent();
            Course course = enrollment.getCourse();

            long totalQuizzes = quizRepository.countByCourse(course);
            long totalAssignments = assignmentRepository.countByCourse(course);
            long totalItems = totalQuizzes + totalAssignments;

            List<QuizSubmission> quizSubs = quizSubmissionRepository.findByStudentAndQuiz_Course(student,course);
            List<AssignmentSubmission> assignSubs = assignmentSubmissionRepository.findByStudentIdAndCourseId(student.getId(),course.getId());

            long completedItems = quizSubs.size() + assignSubs.size();
            double progress = (totalItems > 0) ? ((double) completedItems / totalItems) * 100 : 0;

            double avgQuizScore = quizSubs.stream()
                    .mapToInt(QuizSubmission::getScore)
                    .average().orElse(0.0);

            double avgAssignScore = assignSubs.stream()
                    .filter(s -> s.getScore() != null)
                    .mapToInt(AssignmentSubmission::getScore)
                    .average().orElse(0.0);

            return new StudentReportDTO(
                    student.getId(),
                    student.getName(),
                    course.getId(),
                    course.getName(),
                    progress,
                    avgQuizScore,
                    avgAssignScore,
                    (long) quizSubs.size(),
                    (long) assignSubs.size()
            );
        }).collect(Collectors.toList());
    }
}

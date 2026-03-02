// src/main/java/com/org/cts/edusmart_backend/entity/Attendance.java
package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private boolean isPresent;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student; // This contains the student name

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
}
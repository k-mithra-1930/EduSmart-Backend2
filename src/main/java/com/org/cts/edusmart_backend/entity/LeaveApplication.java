package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "leave_applications")
@Data
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate leaveDate;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student; // Link to your existing User entity
}
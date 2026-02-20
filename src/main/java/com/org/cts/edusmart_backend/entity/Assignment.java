package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assignments")
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate endDate;


    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // Linked to the Course entity

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor; // Linked to the User entity for instructor identification

    @ElementCollection
    @CollectionTable(name = "assignment_questions", joinColumns = @JoinColumn(name = "assignment_id"))
    @Column(name = "question_text")
    private List<String> questions; // Max 10 strings
}
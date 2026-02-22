package com.org.cts.edusmart_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private CourseStatus status;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private User instructor;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Content> contents;

}
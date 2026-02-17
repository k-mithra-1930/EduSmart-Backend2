package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String videoUrl;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}

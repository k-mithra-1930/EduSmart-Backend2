package com.org.cts.edusmart_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "content")
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(nullable = false)
    private String videoTitle;

    @Column(nullable = false)
    private String videoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}

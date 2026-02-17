package com.org.cts.edusmart_backend.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;
    private String fullName;
    private String phone;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

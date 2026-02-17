package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}

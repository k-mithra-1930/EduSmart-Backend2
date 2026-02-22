package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    public List<Content> findByCourseId(Long courseId);
}

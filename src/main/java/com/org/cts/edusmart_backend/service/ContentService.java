package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.ContentDTO;
import com.org.cts.edusmart_backend.dto.ContentResponseDTO;
import com.org.cts.edusmart_backend.entity.Content;
import com.org.cts.edusmart_backend.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContentService {

    public List<ContentResponseDTO> getByCourseId(Long courseId);

    public ContentResponseDTO save(ContentDTO dto);

    public List<ContentResponseDTO> getAll();

    public void delete(Long id);

}

package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.ContentDTO;
import com.org.cts.edusmart_backend.dto.ContentResponseDTO;

import java.util.List;


public interface ContentService {

    public List<ContentResponseDTO> getByCourseId(Long courseId);

    public ContentResponseDTO save(ContentDTO dto);

    public List<ContentResponseDTO> getAll();

    public void delete(Long id);

}

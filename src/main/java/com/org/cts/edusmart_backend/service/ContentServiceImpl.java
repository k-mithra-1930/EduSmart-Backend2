package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.ContentDTO;
import com.org.cts.edusmart_backend.dto.ContentResponseDTO;
import com.org.cts.edusmart_backend.entity.Content;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.repository.ContentRepository;
import com.org.cts.edusmart_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ContentRepository contentRepository;


    @Override
    public List<ContentResponseDTO> getByCourseId(Long courseId) {
        return contentRepository.findByCourseId(courseId).stream().map(content -> {;
            ContentResponseDTO dto = new ContentResponseDTO();
            dto.setId(content.getVideoId());
            dto.setTitle(content.getVideoTitle());
            dto.setUrl(content.getVideoUrl());
            dto.setCourseId(content.getCourse().getId());
            return dto;
        }).toList();
    }

    @Override
    public ContentResponseDTO save(ContentDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found of ID: "+ dto.getCourseId()));

        Content content = new Content();
        content.setVideoTitle(dto.getTitle());
        content.setVideoUrl(dto.getUrl());
        content.setCourse(course);
        Content saved = contentRepository.save(content);
        ContentResponseDTO responseDTO = new ContentResponseDTO();
        responseDTO.setId(saved.getVideoId());
        responseDTO.setTitle(saved.getVideoTitle());
        responseDTO.setUrl(saved.getVideoUrl());
        responseDTO.setCourseId(saved.getCourse().getId());
        return responseDTO;


    }

    @Override
    public List<ContentResponseDTO> getAll() {
        return contentRepository.findAll().stream().map(content -> {
            ContentResponseDTO dto = new ContentResponseDTO();
            dto.setId(content.getVideoId());
            dto.setTitle(content.getVideoTitle());
            dto.setUrl(content.getVideoUrl());
            dto.setCourseId(content.getCourse().getId());
            return dto;
        }).toList();
    }


    @Override
    public void delete(Long id) {
            Content content = contentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Content not found with ID: " + id));
        contentRepository.delete(content);
    }
}

package com.org.cts.edusmart_backend.controller;

import com.org.cts.edusmart_backend.dto.StudentResponseDTO;
import com.org.cts.edusmart_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long studentId) {
        return userRepository.findById(studentId)
                .map(user -> new StudentResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<StudentResponseDTO> getInstructorById(@PathVariable Long instructorId) {
        return userRepository.findById(instructorId)
                // Ensure the user found actually has the INSTRUCTOR role
                .filter(user -> "INSTRUCTOR".equalsIgnoreCase(user.getRole()))
                .map(user -> new StudentResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
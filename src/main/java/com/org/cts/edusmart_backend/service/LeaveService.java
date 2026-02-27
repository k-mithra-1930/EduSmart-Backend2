package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.LeaveRequestDTO;
import com.org.cts.edusmart_backend.dto.LeaveResponseDTO;
import com.org.cts.edusmart_backend.entity.LeaveApplication;
import com.org.cts.edusmart_backend.entity.User;
import com.org.cts.edusmart_backend.repository.LeaveRepository;
import com.org.cts.edusmart_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LeaveApplication leave = new LeaveApplication();
        leave.setStudent(student);
        leave.setLeaveDate(dto.getLeaveDate());
        leave.setReason(dto.getReason());

        LeaveApplication saved = leaveRepository.save(leave);
        return mapToResponseDTO(saved);
    }

    public List<LeaveResponseDTO> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private LeaveResponseDTO mapToResponseDTO(LeaveApplication leave) {
        LeaveResponseDTO response = new LeaveResponseDTO();
        response.setId(leave.getId());
        response.setStudentId(leave.getStudent().getId());
        response.setStudentName(leave.getStudent().getName());
        response.setReason(leave.getReason());
        response.setLeaveDate(leave.getLeaveDate());
        return response;
    }
}
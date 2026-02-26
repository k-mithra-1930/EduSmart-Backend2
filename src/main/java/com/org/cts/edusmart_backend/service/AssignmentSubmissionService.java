package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.AssignmentScoreDTO;
import com.org.cts.edusmart_backend.dto.SubmissionDTO;
import com.org.cts.edusmart_backend.entity.AssignmentSubmission;
import com.org.cts.edusmart_backend.entity.QuestionAnswerPair;
import com.org.cts.edusmart_backend.repository.AssignmentSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentSubmissionService {

    @Autowired
    private AssignmentSubmissionRepository repository;

    public AssignmentSubmission saveSubmission(SubmissionDTO dto) {
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignmentId(dto.getAssignment_id());
        submission.setAssignmentTitle(dto.getAssignment_title());
        submission.setCourseId(dto.getCourse_id());
        submission.setEndDate(dto.getEnddate());
        submission.setSubmittedDate(dto.getSubmitteddate());
        submission.setStudentId(dto.getStudent_id());

        List<QuestionAnswerPair> pairs = dto.getQuestions_answer_pair().stream().map(pairDto -> {
            QuestionAnswerPair pair = new QuestionAnswerPair();
            pair.setQuestion(pairDto.getQuestion());
            pair.setAnswer(pairDto.getAnswer());
            return pair;
        }).collect(Collectors.toList());

        submission.setQuestionsAnswerPair(pairs);
        return repository.save(submission);
    }

    public List<AssignmentSubmission> getAllSubmissions() {
        return repository.findAll();
    }

    public AssignmentSubmission giveScoretoSubmission(AssignmentScoreDTO dto) {
        AssignmentSubmission submission= repository.findById(dto.getAssignmentSubId()).orElseThrow(() -> new RuntimeException("Assignment Submission ID not found"));
        submission.setScore(dto.getScore());
        return repository.save(submission);
    }

    public List<AssignmentSubmission> getSubmissionsByAssignmentId(Long assignmentId) {
        return repository.findByAssignmentId(assignmentId);
    }
}
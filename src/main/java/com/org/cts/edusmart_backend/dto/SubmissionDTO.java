package com.org.cts.edusmart_backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SubmissionDTO {
    private Long assignment_id;
    private String assignment_title;
    private Long course_id;
    private LocalDate enddate;
    private LocalDate submitteddate;
    private Long student_id;
    private List<AnswerPairDTO> questions_answer_pair;
}
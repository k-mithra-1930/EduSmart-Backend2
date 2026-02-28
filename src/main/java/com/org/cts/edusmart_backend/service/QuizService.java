package com.org.cts.edusmart_backend.service;

import com.org.cts.edusmart_backend.dto.QuestionDTO;
import com.org.cts.edusmart_backend.dto.QuizDTO;
import com.org.cts.edusmart_backend.entity.Course;
import com.org.cts.edusmart_backend.entity.Question;
import com.org.cts.edusmart_backend.entity.Quiz;
import com.org.cts.edusmart_backend.repository.CourseRepository;
import com.org.cts.edusmart_backend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz addQuizToCourse(Long courseId, QuizDTO quizDto) {
        // 1. Find the course or throw an error
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // 2. Map QuizDTO to Quiz Entity
        Quiz quiz = new Quiz();
        quiz.setQuestionTitle(quizDto.getQuestionTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setCourse(course);

        // 3. Map List<QuestionDTO> to List<Question> Entities
        if (quizDto.getQuestions() != null) {
            List<Question> questions = quizDto.getQuestions().stream().map(dto -> {
                Question question = new Question();
                question.setTitle(dto.getTitle());
                question.setOptions(dto.getOptions());
                question.setAnswer(dto.getAnswer());
                question.setQuiz(quiz); // Set the back-reference to the quiz
                return question;
            }).collect(Collectors.toList());

            quiz.setQuestions(questions);
        }

        // 4. Save the Quiz (CascadeType.ALL will save questions automatically)
        return quizRepository.save(quiz);
    }

    public List<Quiz> getQuizzesByCourse(Long courseId) {
        return quizRepository.findByCourseId(courseId);
    }

    public void deleteQuiz(Long quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new RuntimeException("Quiz not found with ID: " + quizId);
        }
        quizRepository.deleteById(quizId);
    }

    public List<Quiz> getUnsolvedQuizzes(Long courseId,Long studentId) {
        return quizRepository.findUnsolvedQuizzesByCourse(courseId,studentId);
    }

}
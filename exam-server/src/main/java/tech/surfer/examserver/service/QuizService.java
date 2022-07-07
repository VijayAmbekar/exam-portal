package tech.surfer.examserver.service;

import tech.surfer.examserver.entity.exam.Category;
import tech.surfer.examserver.entity.exam.Quiz;

import java.util.Set;

public interface QuizService {

    Quiz add(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    Set<Quiz> getQuizSet();
    Quiz getQuiz(Long quizId);
    void delete(Long quizId);
}

package tech.surfer.examserver.service;

import tech.surfer.examserver.entity.exam.Question;
import tech.surfer.examserver.entity.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    Question add(Question question);
    Question update(Question question);
    Set<Question> getQuestionSet();
    Question getQuestion(Long questionId);
    void delete(Long questionId);

    Set<Question> getQuestionsOfQuiz(Quiz quiz);
}

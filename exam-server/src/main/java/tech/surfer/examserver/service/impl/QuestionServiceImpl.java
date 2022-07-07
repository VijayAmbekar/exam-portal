package tech.surfer.examserver.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.surfer.examserver.entity.exam.Question;
import tech.surfer.examserver.entity.exam.Quiz;
import tech.surfer.examserver.repo.QuestionRepository;
import tech.surfer.examserver.service.QuestionService;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @NonNull
    private QuestionRepository questionRepository;

    @Override
    public Question add(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question update(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestionSet() {
        return new LinkedHashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public void delete(Long questionId) {
        this.questionRepository.deleteById(questionId);
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }
}

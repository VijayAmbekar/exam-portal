package tech.surfer.examserver.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.surfer.examserver.entity.exam.Quiz;
import tech.surfer.examserver.repo.QuizRepository;
import tech.surfer.examserver.service.QuizService;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    @NonNull
    private QuizRepository quizRepository;

    @Override
    public Quiz add(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizSet() {
        return new LinkedHashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void delete(Long quizId) {
        this.quizRepository.deleteById(quizId);
    }
}

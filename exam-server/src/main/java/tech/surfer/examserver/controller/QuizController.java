package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.surfer.examserver.entity.exam.Quiz;
import tech.surfer.examserver.service.QuizService;
import tech.surfer.examserver.service.QuizService;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class QuizController {

    @NonNull
    private QuizService quizService;

    /**
     * add Quiz
     *
     */
    @PostMapping("/")
    public ResponseEntity addQuiz(@RequestBody Quiz quiz) {
        Quiz quiz1 = this.quizService.add(quiz);
        return ResponseEntity.ok(quiz1);
    }

    /**
     * Get Quiz
     */
    @GetMapping("/{quizId}")
    public ResponseEntity getQuiz(@PathVariable("quizId") Long quizId) {
        return ResponseEntity.ok(this.quizService.getQuiz(quizId));
    }

    /**
     * get all categories
     */
    @GetMapping("/")
    public ResponseEntity getQuizes() {
        return ResponseEntity.ok(this.quizService.getQuizSet());
    }

    /**
     * update quiz
     */
    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz) {
        return this.quizService.updateQuiz(quiz);
    }

    /**
     * delete quiz
     */
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId) {
        this.quizService.delete(quizId);
    }
}

package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.surfer.examserver.entity.exam.Question;
import tech.surfer.examserver.entity.exam.Quiz;
import tech.surfer.examserver.service.QuestionService;
import tech.surfer.examserver.service.QuizService;

import java.util.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class QuestionController {

    @NonNull
    private QuestionService questionService;

    @NonNull
    private QuizService quizService;

    @NonNull
    private Random random;

    /**
     * add Question
     *
     */
    @PostMapping("/")
    public ResponseEntity addQuestion(@RequestBody Question question) {
        Question question1 = this.questionService.add(question);
        return ResponseEntity.ok(question1);
    }

    /**
     * Get Question
     */
    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(this.questionService.getQuestion(questionId));
    }

    /**
     * get all questions
     */
    @GetMapping("/")
    public ResponseEntity getQuestiones() {
        return ResponseEntity.ok(this.questionService.getQuestionSet());
    }

    /**
     * update question
     */
    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return this.questionService.update(question);
    }

    /**
     * delete question
     */
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.delete(questionId);
    }

    /**
     * get all questions
     */
    @GetMapping("/quiz/{qid}")
    public ResponseEntity getQuestionsForQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questionSet = quiz.getQuestionSet();
        Integer numQuestions = Integer.valueOf(quiz.getNumberOfQuestions());

        // convert the Set to arrayList
        List<Question> fullQuestionList = new ArrayList<>(questionSet);
        Collections.shuffle(fullQuestionList, random);

        if(questionSet.size() > numQuestions) {
            fullQuestionList = fullQuestionList.subList(0, numQuestions);
        }

        // get random questions from Set
        return ResponseEntity.ok(fullQuestionList);


//        Quiz quiz = new Quiz();
//        quiz.setQId(qid);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
    }

//    private Set<Question> getRandomQuestions(Set<Question> fullQuestionSet, int maxQuestions) {
//        Set<Question> randomSet= new HashSet<>();
//
//        // convert the Set to arrayList
//        List<Question> fullQuestionList = new ArrayList<>(fullQuestionSet);
//        int fullSetSize = fullQuestionList.size();
//
//        while(randomSet.size() < maxQuestions) {
//            int randomIndex= random.nextInt(fullSetSize);
//
//            randomSet.add(fullQuestionList.get(randomIndex));
//        }
//
//        return randomSet;
//    }
    
}

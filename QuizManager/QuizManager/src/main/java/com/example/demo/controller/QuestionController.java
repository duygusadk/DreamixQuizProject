package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/quiz/{quizId}/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    @GetMapping("/{quizId}/question/get")
    public  ResponseEntity<List<Question>>getQuestions(@PathVariable Long quizId){
        return questionService.findAllQuestionsWithoutAnswers(quizId);


    }
    @GetMapping("/{quizId}/question/getWithAnswers")
    public  ResponseEntity<List<Question>>getQuestionAnswers(@PathVariable Long quizId){
        return questionService.findAllQuestions(quizId);

    }

    @PostMapping("/{quizId}/question/add")
    public ResponseEntity<String> addQuestion(@PathVariable Long quizId, @RequestBody Question question) {

        try {
            questionService.save(question, quizId);
            return new ResponseEntity<>("The question is created", HttpStatus.CREATED);
        } catch (Exception e){
           return new ResponseEntity<>("The question is not created", HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/question/update/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        try {
             questionService.update(updatedQuestion,id);
            return new ResponseEntity<>("The question is updated", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("The question is not updated", HttpStatus.BAD_REQUEST);

        }

    }
    @DeleteMapping("/question/delete/{id}")
    public ResponseEntity<String>deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteById(id);
            return new ResponseEntity<>("The question is deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("The question is not deleted", HttpStatus.BAD_REQUEST);

        }

    }
}

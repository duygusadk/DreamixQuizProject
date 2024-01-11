package com.example.demo.controller;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.UserResponse;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;


    @GetMapping("/get")
    public List<Quiz> getAllQuizzes(){
        return quizService.findAllQuiz();
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addQuiz(@PathVariable Long userId,@RequestBody Quiz quiz){
        try {
            quizService.save(quiz, userId);
            return new ResponseEntity<>("The quiz is created", HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>("The quiz is not created",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path ="/{id}/submit")
    public ResponseEntity<String>submitQuiz(@PathVariable Long id,@RequestBody List<UserResponse>response){

        return quizService.calculateResult(id,response);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        try {
            quizService.update(updatedQuiz,id);
            return new ResponseEntity<>("The quiz is updated", HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>("The quiz is not updated",HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        try {
            quizService.deleteById(id);
            return new ResponseEntity<>("The quiz is deleted", HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>("The quiz is not deleted",HttpStatus.BAD_REQUEST);
        }

    }

}

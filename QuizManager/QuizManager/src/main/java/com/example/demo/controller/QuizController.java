package com.example.demo.controller;

import com.example.demo.dto.request.QuizRequestDto;
import com.example.demo.dto.response.QuizResponseDto;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.UserResponse;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
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


    @GetMapping
    public List<QuizResponseDto> getAllQuizzes(){
        return quizService.findAllQuiz();
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<QuizResponseDto> addQuiz(@PathVariable Long userId,@Valid @RequestBody QuizRequestDto quiz){
        try {
            return new ResponseEntity<>(quizService.create(quiz, userId), HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @Valid @RequestBody QuizRequestDto updatedQuiz) {
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

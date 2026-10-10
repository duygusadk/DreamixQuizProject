package com.example.demo.service;

import com.example.demo.dto.request.QuizRequestDto;
import com.example.demo.dto.response.QuizResponseDto;
import com.example.demo.entity.Question;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.mapper.QuizMapper;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizMapper quizMapper;

    public List<QuizResponseDto> findAllQuiz(){
       return quizRepository.findAll().stream().map(quizMapper::toDto).collect(Collectors.toList());
    }


    public QuizResponseDto findById(Long id) {
        return quizRepository.findById(id).stream().map(quizMapper::toDto).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Quiz not found with id: " + id));
    }

    public QuizResponseDto create(QuizRequestDto quiz, Long userId) {

        User user=userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with id: "+userId));
        Quiz quizEntity=quizMapper.toEntity(quiz);
        user.getQuizzes().add(quizEntity);
        userRepository.save(user);

        return quizMapper.toDto(quizEntity);
    }
    public QuizResponseDto update(QuizRequestDto quiz,Long id){

        Quiz updatedQuiz=quizRepository.findById(id).get();
        if(updatedQuiz.getId()==null){throw new NoSuchElementException();}
        updatedQuiz.setTitle(quiz.getTitle());
        updatedQuiz.setDescription(quiz.getDescription());

        return quizMapper.toDto(quizRepository.save(updatedQuiz));

    }
    public void deleteById(Long id) {
        quizRepository.deleteById(id);

    }

}

package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.UserResponse;
import com.example.demo.entity.Users;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Quiz> findAllQuiz(){
        return quizRepository.findAll();
    }


    public Quiz findById(Long id){
        return  quizRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Quiz not found with ID: " + id));
    }


    public ResponseEntity<Quiz> save(Quiz quiz, Long userId) {

        Users user=userRepository.findById(userId).get();
        if(user.getId()==null){ throw new NoSuchElementException();}
        quiz.setUsers(user);
        user.getQuizzes().add(quiz);
        userRepository.save(user);

        return new ResponseEntity<>(quizRepository.save(quiz),HttpStatus.CREATED);
    }
    public Quiz update(Quiz quiz,Long id){

        Quiz updatedQuiz=quizRepository.findById(id).get();
        if(updatedQuiz.getId()==null){throw new NoSuchElementException();}
        updatedQuiz.setTitle(quiz.getTitle());
        updatedQuiz.setDescription(quiz.getDescription());

        return quizRepository.save(updatedQuiz);

    }
    public void deleteById(Long id) {
        quizRepository.deleteById(id);

    }

    public ResponseEntity<String> calculateResult(Long id, List<UserResponse> responses) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        if(quiz.isEmpty()){throw  new NoSuchElementException();}
        List<Question>questions=quiz.get().getQuestions();
        int i=0;
        int rightAnswers=0;
        for(UserResponse response:responses){
            if(response.getResponse().equals(questions.get(i).getCorrectAnswer())){
                 rightAnswers=rightAnswers+questions.get(i).getPoint();

            }
          i++;
        }
        return  new ResponseEntity<>("Result:"+rightAnswers,HttpStatus.OK);
    }
}

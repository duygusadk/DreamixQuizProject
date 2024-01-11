package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    public ResponseEntity<List<Question>>findAllQuestions(Long id){
        try {
            Optional<Quiz> quiz=quizRepository.findById(id);
            List<Question>questions=quiz.get().getQuestions();

            return new ResponseEntity<>(questions, HttpStatus.OK);

        }catch (Exception e){

            throw new NoSuchElementException();
        }

    }
    public ResponseEntity<List<Question>> findAllQuestionsWithoutAnswers(Long quizId) {
        Optional<Quiz> quiz=quizRepository.findById(quizId);

        List<Question>questions=quiz.get().getQuestions();
        List<Question>forUser=new ArrayList<>();

        for (Question q :questions){
            Question q1=new Question(q.getId(),q.getContent(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getPoint());
            forUser.add(q1);
        }
        return new ResponseEntity<>(forUser,HttpStatus.OK);
    }

    public Question findById(Long id){

           return questionRepository.findById(id).get();

    }
    public Question save(Question question, Long quizId) {

        Optional<Quiz> quiz = quizRepository.findById(quizId);

        if(quiz.isEmpty()){
            throw new NoSuchElementException("The Quiz not found with Id:"+quizId);
        }
        question.setQuiz(quiz.get());

        return questionRepository.save(question);
    }
    public Question update(Question question,Long id){
        Question updatedQuestion=questionRepository.findById(id).get();
        if(updatedQuestion.getId()==null){throw new NoSuchElementException();}

        updatedQuestion.setContent(question.getContent());
        updatedQuestion.setOption1(question.getOption1());
        updatedQuestion.setOption2(question.getOption2());
        updatedQuestion.setOption3(question.getOption3());
        updatedQuestion.setOption4(question.getOption4());
        updatedQuestion.setCorrectAnswer(question.getCorrectAnswer());
        updatedQuestion.setPoint(question.getPoint());
        return  questionRepository.save(updatedQuestion);
    }
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }


}

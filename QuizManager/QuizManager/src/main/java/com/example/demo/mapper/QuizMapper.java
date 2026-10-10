package com.example.demo.mapper;

import com.example.demo.dto.request.QuizRequestDto;
import com.example.demo.dto.response.QuizResponseDto;
import com.example.demo.entity.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizResponseDto toDto(Quiz quiz) {
        QuizResponseDto dto = new QuizResponseDto();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        return dto;
    }
    public Quiz toEntity(QuizRequestDto dto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        return quiz;
    }
}

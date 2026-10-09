package com.example.demo.mapper;

import com.example.demo.dto.response.QuizResponseDto;
import com.example.demo.entity.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizResponseDto toResponseDto(Quiz quiz) {
        QuizResponseDto dto = new QuizResponseDto();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        return dto;
    }
    public Quiz toEntity(QuizResponseDto dto) {
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        return quiz;
    }
}

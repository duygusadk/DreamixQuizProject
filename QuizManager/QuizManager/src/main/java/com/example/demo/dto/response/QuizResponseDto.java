package com.example.demo.dto.response;

import com.example.demo.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizResponseDto {
    private Long id;
    private String title;
    private String description;
    private List<Question> questions;
}

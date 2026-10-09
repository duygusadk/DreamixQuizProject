package com.example.demo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionRequestDto {

    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int point;
}

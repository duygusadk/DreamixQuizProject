package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String content;
    @NonNull
    private String option1;
    @NonNull
    private String option2;
    @NonNull
    private String option3;
    private String option4;
    @NonNull
    private String correctAnswer;
    private int point;

    @NonNull
    @JsonIgnore
    //delete ne iztrivase dobavihme Lazy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;


    public Question(@NonNull String content, @NonNull String option1, @NonNull String option2, @NonNull String option3, String option4, @NonNull String correctAnswer, int point, @NonNull Quiz quiz) {
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.point = point;
        this.quiz = quiz;
    }

    public Question(Long id, @NonNull String content, @NonNull String option1, @NonNull String option2, @NonNull String option3, String option4,int point) {
        this.id=id;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.point=point;
    }
}

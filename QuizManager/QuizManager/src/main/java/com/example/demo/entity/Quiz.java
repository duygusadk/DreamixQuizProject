package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.List;

@Entity
@Table(name="quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class Quiz {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank
        private String title;

        private String description;

        @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Question> questions;
        @NonNull @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;


    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;

    }


}


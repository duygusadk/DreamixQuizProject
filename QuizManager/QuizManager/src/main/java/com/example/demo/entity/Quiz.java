package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.ArrayList;
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


        @NonNull
        private String title;

        private String description;

       // @JsonIgnore
        @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Question> questions;
        @NonNull @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "users_id", referencedColumnName = "id")
        private Users users;


    public Quiz(@NonNull String title, String description) {
        this.title = title;
        this.description = description;

    }


}


package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String fName;
    @NonNull
    private String lName;
    @NonNull
    private String password;
    @NonNull
    private String email;

    @OneToMany (mappedBy = "users", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

}


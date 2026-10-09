package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fName;
    @NotBlank
    private String lName;
    @NotBlank
    private String password;
    @Email
    private String email;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

}


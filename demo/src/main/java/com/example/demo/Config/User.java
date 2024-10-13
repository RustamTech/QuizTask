package com.example.demo.Config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String username;
    private int currentQuestion = 0;
    private int correctAnswers = 0;

    public User(String username) {
        this.username = username;
    }
}


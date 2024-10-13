package com.example.demo.Config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Question {
    private String questionText;
    private String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }
}

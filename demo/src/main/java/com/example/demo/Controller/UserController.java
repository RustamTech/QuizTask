package com.example.demo.Controller;

import com.example.demo.Config.Question;
import com.example.demo.Service.QuestionService;
import com.example.demo.Config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private Map<String, User> users = new HashMap<>();

    @Autowired
    private QuestionService questionService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username));
            return "User is register";
        } else {
            return "User is already exist";
        }
    }

    @GetMapping("/question")
    public String getQuestion(@RequestParam String username) {
        User user = users.get(username);
        // first we should check if this user is exist
        if (user == null) {
            return "User dont register, try again";
        }

        int currentQuestionIndex = user.getCurrentQuestion();
        Question question = questionService.getQuestion(currentQuestionIndex);

        if (question != null) {
            return "Question " + ((Question) question).getQuestionText();
        } else {
            return "You have answered all question";
        }
    }

    @PostMapping("/answer")
    public String submitAnswer(@RequestParam String username, @RequestParam String answer) {
        User user = users.get(username);
        if (user == null) {
            return "user dont exist";
        }

        int currentQuestionIndex = user.getCurrentQuestion();
        if (questionService.checkAnswer(currentQuestionIndex, answer)) {
            user.setCurrentQuestion(currentQuestionIndex + 1);
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);

            if (user.getCurrentQuestion() >= questionService.getTotalQuestions()) {
                return "You have already answered all question";

            } else {
                return "Answer correct, check the next one";
            }
        } else {
            return "You noob, try again :3";
        }
    }

    @GetMapping("/correct-answers")
    public String getCorrectAnswers(@RequestParam String username) {
        User user = users.get(username);
        if (user == null) {
            return "User dont exist!";
        }
        return "Количество правильных ответов: " + user.getCorrectAnswers();
    }

    @GetMapping("/percentage")
    public String getCorrectAnswerPercentage(@RequestParam String username) {
        User user = users.get(username);
        if (user == null) {
            return "Your user dont exist";
        }

        int correctAnswers = user.getCorrectAnswers();
        int totalQuestions = questionService.getTotalQuestions();

        double percentage = ((double) correctAnswers / totalQuestions) * 100;
        return String.format("Check your answer in %.2f%%", percentage);
    }
}



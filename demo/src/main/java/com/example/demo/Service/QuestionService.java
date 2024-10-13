package com.example.demo.Service;

import com.example.demo.Config.Question;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private List<Question> questions = new ArrayList<>();

    public QuestionService() {
        questions.add(new Question("Сколько будет 2+2?", "4"));
        questions.add(new Question("Сколько будет 3+3?", "6"));
        questions.add(new Question("Сколько будет 4+4?", "8"));
        questions.add(new Question("Сколько будет 5+5?", "10"));
        questions.add(new Question("Сколько будет 6+7?", "13"));

    }

    public Question getQuestion(int questionIndex) { // checking index of each question
        if (questionIndex < questions.size()) {
            return questions.get(questionIndex);
        }
        return null;
    }

    public boolean checkAnswer(int questionIndex, String userAnswer) {
        Question question = getQuestion(questionIndex);
        if (question != null) {
            return question.getCorrectAnswer().equalsIgnoreCase(userAnswer);
        }
        return false;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}

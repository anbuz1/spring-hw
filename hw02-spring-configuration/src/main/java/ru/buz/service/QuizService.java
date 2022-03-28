package ru.buz.service;

import ru.buz.domain.Answer;
import ru.buz.domain.Question;

import java.util.List;

public interface QuizService {
    Question nextQuestion();
    boolean checkAnswer(String answer);
    List<Answer> getAnswerOptions();
    Answer getRightAnswer();
}

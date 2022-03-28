package ru.buz.service;

import ru.buz.domain.Answer;
import ru.buz.domain.Question;
import ru.buz.domain.Questionnaire;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class QuizServiceImpl implements QuizService {
    private final Questionnaire questionnaire;
    private final Queue<Question> questions;
    private Question currentQuestion;
    private Answer rightAnswer;

    public QuizServiceImpl(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
        questions = new LinkedList<>(questionnaire.getAllQuestions());
    }

    @Override
    public Question nextQuestion() {
        currentQuestion = questions.poll();
        rightAnswer = questionnaire.getRightAnswer(currentQuestion);
        return currentQuestion;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return rightAnswer.getAnswer().equals(answer);
    }

    @Override
    public List<Answer> getAnswerOptions() {
        return questionnaire.getAnswers(currentQuestion);
    }

    @Override
    public Answer getRightAnswer() {
        return rightAnswer;
    }
}

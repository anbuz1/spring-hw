package ru.buz.domain;

import java.util.List;
import java.util.Set;

public interface Questionnaire {
    List<Answer> getAnswers(Question question);
    Answer getRightAnswer(Question question);
    Set<Question> getAllQuestions();
}

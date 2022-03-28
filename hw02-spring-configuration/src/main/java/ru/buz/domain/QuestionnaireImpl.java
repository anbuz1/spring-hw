package ru.buz.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionnaireImpl implements Questionnaire{
    private Map<Question, List<Answer>> questionMap;

    public QuestionnaireImpl(Map<Question, List<Answer>> questionMap) {
        this.questionMap = questionMap;
    }

    @Override
    public List<Answer> getAnswers(Question question) {
        return questionMap.get(question);
    }

    @Override
    public Answer getRightAnswer(Question question) {
        List<Answer> answers = questionMap.get(question);
        if (answers!=null){
            for (Answer answer : answers) {
                if(answer.isRight()){
                    return answer;
                }
            }
        }
        return null;
    }

    @Override
    public Set<Question> getAllQuestions() {
        return questionMap.keySet();
    }
}

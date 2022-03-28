package ru.buz.config;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.buz.domain.Answer;
import ru.buz.domain.Question;
import ru.buz.domain.Questionnaire;
import ru.buz.domain.QuestionnaireImpl;
import ru.buz.service.IOService;
import ru.buz.service.IOServiceImpl;
import ru.buz.service.QuizService;
import ru.buz.service.QuizServiceImpl;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;


@ComponentScan(basePackages = "ru.buz")
@Configuration
public class QuestionConfig {

    @Bean
    Questionnaire questionnaire (){
        URL resource = this.getClass().getClassLoader().getResource("question.csv");
        Map<Question, List<Answer>> result = new HashMap<>();
        try {
            assert resource != null;
            try(CSVReader reader = new CSVReader(new FileReader(resource.getFile()), ',' , '"' , 1)
            ) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    List<Answer> answers = new ArrayList<>();
                    Question question = new Question(nextLine[0]);
                    for (int i = 1; i < nextLine.length; i++) {
                        Answer answer = new Answer(nextLine[i]);
                        answers.add(answer);
                        if(i == nextLine.length-1){
                            answer.setRight(true);
                        }
                    }
                    result.put(question,answers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new QuestionnaireImpl(result);
    }

    @Bean
    IOService ioService(@Autowired QuizService quizService){
        return new IOServiceImpl(quizService);
    }

    @Bean QuizService quizService(@Autowired Questionnaire questionnaire){
        return new QuizServiceImpl(questionnaire);
    }

}

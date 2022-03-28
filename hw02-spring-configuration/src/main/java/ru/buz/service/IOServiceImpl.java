package ru.buz.service;

import ru.buz.domain.Answer;
import ru.buz.domain.Question;

import java.util.Scanner;

public class IOServiceImpl implements IOService {
    private final QuizService quizService;

    public IOServiceImpl(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public void start() {
        Question question;
        Scanner in = new Scanner(System.in);
        int count = 1;
        System.out.println("HELLO");
        System.out.println("---------------------");
        while ((question = quizService.nextQuestion()) != null) {
            System.out.println(count++ + ". " + question.getQuestion());
            System.out.println("Answer options: ");
            printAnswerOptions();
            String answer = in.nextLine();
            if (quizService.checkAnswer(answer)) {
                System.out.println("correct!");
            } else {
                System.out.println("Wrong! Right answer is: ");
                printRightAnswer();
            }
            System.out.println("_______________________________________");
        }
    }


    @Override
    public void printAnswerOptions() {
        char count = 'a';
        for (Answer answerOption : quizService.getAnswerOptions()) {
            System.out.println(count++ + ". " + answerOption.getAnswer());
        }
        System.out.println("Write full answer:");
    }

    @Override
    public void printRightAnswer() {
        System.out.println(quizService.getRightAnswer().getAnswer());
    }

}

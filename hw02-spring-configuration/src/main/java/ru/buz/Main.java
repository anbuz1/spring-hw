package ru.buz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.buz.domain.Questionnaire;
import ru.buz.service.IOService;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        IOService ioService = context.getBean(IOService.class);
        ioService.start();

    }

}

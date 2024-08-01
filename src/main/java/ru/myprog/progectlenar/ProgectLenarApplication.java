package ru.myprog.progectlenar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableKafka
// todo ну серьёзно?)))
// todo progggggect
public class ProgectLenarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgectLenarApplication.class, args);
    }
}

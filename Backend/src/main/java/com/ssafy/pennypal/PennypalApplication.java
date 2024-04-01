package com.ssafy.pennypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableJpaAuditing
public class PennypalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PennypalApplication.class, args);
    }

}

package com.github.irbis.games.rps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RockPaperScissorsGame implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(RockPaperScissorsGame.class);

    public static void main( String[] args ) {
        SpringApplication.run(RockPaperScissorsGame.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world!");
    }
}

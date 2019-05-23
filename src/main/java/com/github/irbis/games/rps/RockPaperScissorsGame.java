package com.github.irbis.games.rps;

import com.github.irbis.games.rps.state.GameState;
import com.github.irbis.games.rps.state.WelcomeGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RockPaperScissorsGame implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(RockPaperScissorsGame.class);

    @Autowired
    private WelcomeGameState welcomeGameState;

    @Override
    public void run(String... args) throws Exception {
        GameState game = welcomeGameState;

        Scanner sc = new Scanner(System.in);
        String input;

        do {
            game.show();
            input = sc.nextLine();
            game = game.act(input);
        } while (game.isContinue());

        game.show();
    }

    public static void main( String[] args ) {
        SpringApplication.run(RockPaperScissorsGame.class, args);
    }

}

package com.github.irbis.games.rps.service;

import com.github.irbis.games.rps.state.Turn;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomComputerPlayerService implements ComputerPlayerService {

    private final Random random = new Random();

    @Override
    public Turn turn() {
        switch(random.nextInt(2)) {
            case 0:
                return Turn.ROCK;
            case 1:
                return Turn.PAPER;
            default:
                return Turn.SCISSOR;
        }
    }
}

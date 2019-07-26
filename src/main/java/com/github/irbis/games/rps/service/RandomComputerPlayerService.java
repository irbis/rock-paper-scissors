package com.github.irbis.games.rps.service;

import com.github.irbis.games.rps.state.Turn;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Random;

@Service
public class RandomComputerPlayerService implements ComputerPlayerService {

    private final Random random = new Random();
    private final LinkedList<Turn> gamerTurns = new LinkedList<>();

    private class GameTurnsStatistic {
        long rockTurnsCount = 0;
        long paperTurnsCount = 0;
        long scissorTurnsCount = 0;
    }

    @Override
    public Turn turn() {
        GameTurnsStatistic stat = calculateGamerStatistic();
        Turn nextTurn;

        if (stat.rockTurnsCount < stat.paperTurnsCount) {
            if (stat.rockTurnsCount < stat.scissorTurnsCount) nextTurn = Turn.ROCK;
            else nextTurn = Turn.SCISSOR;
        } else {
            if (stat.paperTurnsCount < stat.scissorTurnsCount) nextTurn = Turn.PAPER;
            else nextTurn = Turn.SCISSOR;
        }

        return nextTurn;
    }

    @Override
    public void saveGamerTurn(Turn gamerTurn) {
        gamerTurns.add(gamerTurn);
    }

    private GameTurnsStatistic calculateGamerStatistic() {
        GameTurnsStatistic stat = new GameTurnsStatistic();

        stat.rockTurnsCount = gamerTurns.stream().filter(turn -> turn == Turn.ROCK).count();
        stat.paperTurnsCount = gamerTurns.stream().filter(turn -> turn == Turn.PAPER).count();
        stat.scissorTurnsCount = gamerTurns.stream().filter(turn -> turn == Turn.SCISSOR).count();

        return stat;
    }
}

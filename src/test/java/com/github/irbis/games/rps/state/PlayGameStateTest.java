package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.ComputerPlayerService;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayGameStateTest {
    protected final String ROCK_KEY = "1";
    protected final String PAPER_KEY = "2";
    protected final String SCISSORS_KEY = "3";

    protected final ComputerPlayerService COMPUTER_TURN_ROCK = new ComputerPlayerService() {
        @Override
        public Turn turn() {
            return Turn.ROCK;
        }

        @Override
        public void saveGamerTurn(Turn gamerTurn) {

        }
    };

    protected final ComputerPlayerService COMPUTER_TURN_PAPER = new ComputerPlayerService() {
        @Override
        public Turn turn() {
            return Turn.PAPER;
        }

        @Override
        public void saveGamerTurn(Turn gamerTurn) {

        }
    };

    protected final ComputerPlayerService COMPUTER_TURN_SCISSORS = new ComputerPlayerService() {
        @Override
        public Turn turn() {
            return Turn.SCISSOR;
        }

        @Override
        public void saveGamerTurn(Turn gamerTurn) {

        }
    };

    @Autowired private MessageResolver messageResolver;
    @Autowired private StartGameState startGameState;
    @Autowired private GameState incorrectCommandGameState;
    @Autowired private SessionService sessionService;
    @Autowired private StatisticService statisticService;

    protected PlayGameState createPlayGameState(ComputerPlayerService computerPlayerService) {
        return new PlayGameState(
                messageResolver,
                startGameState,
                incorrectCommandGameState,
                sessionService,
                computerPlayerService,
                statisticService);
    }
}

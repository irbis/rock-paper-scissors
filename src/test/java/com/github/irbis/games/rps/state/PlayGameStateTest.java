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

    protected final ComputerPlayerService COMPUTER_TURN_ROCK = () -> Turn.ROCK;

    protected final ComputerPlayerService COMPUTER_TURN_PAPER = () -> Turn.PAPER;

    protected final ComputerPlayerService COMPUTER_TURN_SCISSORS = () -> Turn.SCISSOR;

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

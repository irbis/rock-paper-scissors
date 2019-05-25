package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.ComputerPlayerService;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PlayGameState extends GameState {

    private final StartGameState startGameState;
    private final GameState incorrectCommandGameState;
    private final SessionService sessionService;
    private final ComputerPlayerService computerPlayerService;
    private final StatisticService statisticService;

    public PlayGameState(
            MessageResolver messageResolver,
            @Lazy StartGameState startGameState,
            @Lazy GameState incorrectCommandGameState,
            SessionService sessionService,
            ComputerPlayerService computerPlayerService,
            StatisticService statisticService) {
        super(messageResolver);
        this.startGameState = startGameState;
        this.incorrectCommandGameState = incorrectCommandGameState;
        this.sessionService = sessionService;
        this.computerPlayerService = computerPlayerService;
        this.statisticService = statisticService;
    }

    @Override
    public void show() {
        printlnMessage("lets-play", sessionService.getCurrentSession());
        printlnMessage("make-choice");
        printlnMessage("rock-choice");
        printlnMessage("paper-choice");
        printlnMessage("scissor-choice");
    }

    @Override
    public GameState act(String input) {
        Turn compTurn = computerPlayerService.turn();
        Turn gamerTurn = parseInput(input);

        printlnMessage("you-choice", gamerTurn.toString().toLowerCase());
        printlnMessage("my-choice", compTurn.toString().toLowerCase());

        switch(gamerTurn) {
            case ROCK:
                return createGameResultState(checkRock(compTurn));
            case PAPER:
                return createGameResultState(checkPaper(compTurn));
            case SCISSOR:
                return createGameResultState(checkScissor(compTurn));
        }

        return incorrectCommandGameState;
    }

    private Turn parseInput(String input) {
        if (input.equals("1") || input.equalsIgnoreCase("a"))
            return Turn.ROCK;
        else if (input.equals("2") || input.equalsIgnoreCase("s"))
            return Turn.PAPER;
        else if (input.equalsIgnoreCase("3") || input.equalsIgnoreCase("d"))
            return Turn.SCISSOR;

        return Turn.UNKNOWN;
    }

    private GameResultState createGameResultState(GameResult gameResult) {
        return new GameResultState(
                messageResolver,
                gameResult,
                startGameState,
                sessionService,
                statisticService);
    }

    private GameResult checkRock(Turn computerTurn) {
        switch (computerTurn) {
            case ROCK:
                return GameResult.DRAW;
            case PAPER:
                return GameResult.LOOSE;
            default:
                return GameResult.WIN;
        }
    }

    private GameResult checkPaper(Turn computerTurn) {
        switch (computerTurn) {
            case ROCK:
                return GameResult.WIN;
            case PAPER:
                return GameResult.DRAW;
            default:
                return GameResult.LOOSE;
        }
    }

    private GameResult checkScissor(Turn computerTurn) {
        switch (computerTurn) {
            case ROCK:
                return GameResult.LOOSE;
            case PAPER:
                return GameResult.WIN;
            default:
                return GameResult.DRAW;
        }
    }
}

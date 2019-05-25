package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;

public class GameResultState extends GameState {

    private final GameResult gameResult;
    private final StartGameState startGameState;
    private final SessionService sessionService;
    private final StatisticService statisticService;

    public GameResultState(
            MessageResolver messageResolver,
            GameResult gameResult,
            StartGameState startGameState,
            SessionService sessionService,
            StatisticService statisticService) {
        super(messageResolver);
        this.gameResult = gameResult;
        this.startGameState = startGameState;
        this.sessionService = sessionService;
        this.statisticService = statisticService;
    }

    @Override
    public void show() {
        String username = sessionService.getCurrentSession();

        switch (gameResult) {
            case WIN:
                printlnMessage("win");
                statisticService.win(username);
                break;
            case LOOSE:
                printlnMessage("loose");
                statisticService.fail(username);
                break;
            default:
                printlnMessage("draw");
        }

        printlnMessage("enter-to-continue");
    }

    @Override
    public GameState act(String input) {
        return startGameState;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}

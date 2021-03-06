package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;
import org.springframework.stereotype.Component;

@Component
public class WelcomeGameState extends GameState {

    private final StartGameState startGameState;
    private final ExitGameState exitGameState;
    private final SessionService sessionService;
    private final StatisticService statisticService;

    public WelcomeGameState(
            MessageResolver messageResolver,
            StartGameState startGameState,
            ExitGameState exitGameState,
            SessionService sessionService,
            StatisticService statisticService) {
        super(messageResolver);
        this.startGameState = startGameState;
        this.exitGameState = exitGameState;
        this.sessionService = sessionService;
        this.statisticService = statisticService;
    }

    @Override
    public void show() {
        printlnMessage("welcome");
        printMessage("login");
    }

    @Override
    public GameState act(String input) {
        if (input.trim().isEmpty()) {
            return exitGameState;
        } else {
            sessionService.createSession(input);
            statisticService.initStatistic(sessionService.getCurrentSession());
            return startGameState;
        }
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}

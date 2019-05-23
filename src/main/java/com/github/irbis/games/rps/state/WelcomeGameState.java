package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.sun.tools.javadoc.Start;
import org.springframework.stereotype.Component;

@Component
public class WelcomeGameState extends GameState {

    private final StartGameState startGameState;
    private final ExitGameState exitGameState;
    private final SessionService sessionService;

    public WelcomeGameState(
            MessageResolver messageResolver,
            StartGameState startGameState,
            ExitGameState exitGameState,
            SessionService sessionService) {
        super(messageResolver);
        this.startGameState = startGameState;
        this.exitGameState = exitGameState;
        this.sessionService = sessionService;
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
            return startGameState;
        }
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}

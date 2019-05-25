package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import org.springframework.stereotype.Component;

@Component
public class IncorrectCommandGameState extends GameState {
    private final StartGameState startGameState;

    public IncorrectCommandGameState(MessageResolver messageResolver,
                                     StartGameState startGameState) {
        super(messageResolver);
        this.startGameState = startGameState;
    }

    @Override
    public void show() {
        printlnMessage("incorrect-command");
    }

    @Override
    public GameState act(String input) {
        return startGameState;
    }
}
